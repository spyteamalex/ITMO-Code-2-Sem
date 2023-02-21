package expression.exceptions;

import expression.AlgebraicExpression;
import expression.Const;
import expression.Priority;
import expression.Variable;
import expression.exceptions.exceptions.ParseExpressionException;

import java.util.List;
import java.util.function.BinaryOperator;

public class ExpressionParser implements TripleParser {
    private TokenReader source;
    private record BinaryOperationUnit(String operator, Priority priority, BinaryOperator<AlgebraicExpression> constructor) {}

    private static final List<BinaryOperationUnit> BINARY_OPERATIONS = List.of(
            binary("+", Priority.SUM, CheckedAdd::new),
            binary("-", Priority.SUM, CheckedSubtract::new),
            binary("*", Priority.MULTIPLY, CheckedMultiply::new),
            binary("/", Priority.DIVIDE, CheckedDivide::new),
            binary("gcd", Priority.GCD, CheckedGCD::new),
            binary("lcm", Priority.LCM, CheckedLCM::new)
    );

    private static BinaryOperationUnit binary(
            final String operator,
            final Priority priority,
            final BinaryOperator<AlgebraicExpression> constructor
    ) {
        return new BinaryOperationUnit(operator, priority, constructor);
    }

    public ExpressionParser() {
    }

    public AlgebraicExpression parse(String expression) throws ParseExpressionException {
        source = new TokenReader(new StringSource(expression));
        AlgebraicExpression result = parseClause(Priority.MIN);
        if (!source.eof()) {
            throw new ParseExpressionException("Unexpected data at position " + source.getPosition() + " found: " + source.take());
        }
        return result;
    }

    public AlgebraicExpression parseClause(Priority minPriority) throws ParseExpressionException {
        if (source.eof()) {
            throw new ParseExpressionException("Unexpected end of data at position " + source.getPosition());
        }
        if (source.test(")")) {
            throw new ParseExpressionException("Unexpected end of clause at position " + source.getPosition());
        }
        AlgebraicExpression firstOperand = parseOperand();
        while (!source.eof() && !source.test(")")) {
            AlgebraicExpression buffer = parseBinary(firstOperand, minPriority);
            if (buffer == null) {
                break;
            }
            firstOperand = buffer;
        }
        return firstOperand;
    }

    private AlgebraicExpression parseOperand() throws ParseExpressionException {
        AlgebraicExpression firstOperand;
        if (source.take("(")) {
            firstOperand = parseClause(Priority.MIN);
            if (!source.take(")")) {
                throw new ParseExpressionException("End of clause expected at position " + source.getPosition() + ", but not found");
            }
        } else if (Character.isDigit(source.get().charAt(0))) {
            firstOperand = parseConst();
        } else {
            firstOperand = parseUnary();
        }
        return firstOperand;
    }

    private AlgebraicExpression parseConst() {
        return parseConst("");
    }

    private AlgebraicExpression parseConst(String prefix) {
        return new Const(Integer.parseInt(prefix + source.take()));
    }

    private AlgebraicExpression parseUnary() throws ParseExpressionException {
        int position = source.getPosition();
        String token = source.take();
        return switch (token) {
            case "-" -> {
                if ((source.getPosition() - position) > token.length() || !Character.isDigit(source.get().charAt(0))) {
                    yield new CheckedNegate(parseClause(Priority.MAX));
                } else {
                    yield parseConst("-");
                }
            }
            // todo мапа
            // todo указывать, позицию, где падает
            // todo индексация с 1
            case "reverse" -> new CheckedReverse(parseClause(Priority.MAX));
            case "pow10" -> new CheckedPow10(parseClause(Priority.MAX));
            case "log10" -> new CheckedLog10(parseClause(Priority.MAX));
            case "x", "y", "z" -> new Variable(token);
            default -> throw unknownOperatorException(position, token, "unary");
        };
    }

    private AlgebraicExpression parseBinary(AlgebraicExpression firstOperand, Priority minPriority) throws ParseExpressionException {
        for (BinaryOperationUnit operation : BINARY_OPERATIONS) {
            if (!source.test(operation.operator)) {
                continue;
            }
            if (minPriority.compareTo(operation.priority) > 0) {
                return null;
            }
            source.take();
            return operation.constructor.apply(firstOperand, parseClause(operation.priority.higher()));
        }
        throw unknownOperatorException(source.getPosition(), source.get(), "binary");
    }

    private static ParseExpressionException unknownOperatorException(int position, String operator, String operatorType) {
        return new ParseExpressionException("Unknown " + operatorType + " operator found at position " + position + ": " + operator);
    }
}
