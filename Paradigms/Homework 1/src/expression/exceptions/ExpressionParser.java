package expression.exceptions;

import expression.AlgebraicExpression;
import expression.Const;
import expression.Priority;
import expression.Variable;
import expression.exceptions.exceptions.ParseExpressionException;

import java.util.List;
import java.util.function.BinaryOperator;

public class ExpressionParser implements TripleParser {
    public ExpressionParser() {
        super();
    }

    private StringReader source;

    public AlgebraicExpression parse(String expression) throws ParseExpressionException {
        source = new StringReader(new StringSource(expression));
        AlgebraicExpression result = parseExpression(Priority.ZERO);
        skipSpaces();
        if (!source.eof()) {
            throw new ParseExpressionException("Unexpected data found");
        }
        return result;
    }

    private AlgebraicExpression parseExpression(Priority minPriority) throws ParseExpressionException {
        AlgebraicExpression firstOperand;
        skipSpaces();
        if (source.eof()) {
            throw new ParseExpressionException("Unexpected end of file");
        }
        if (source.take('(')) {
            firstOperand = parseExpression(Priority.ZERO);
            if (!source.take(')')) {
                throw new ParseExpressionException(") expected, but not found");
            }
        } else if (Character.isDigit(source.test())) {
            firstOperand = parseConst();
        } else {
            firstOperand = parseUnary(minPriority);
        }
        AlgebraicExpression buffer = parseBinary(firstOperand, minPriority);
        do {
            firstOperand = buffer;
            buffer = parseBinary(firstOperand, minPriority);
        } while (buffer != firstOperand);
        return firstOperand;
    }

    private AlgebraicExpression parseConst() {
        return parseConst("");
    }

    private AlgebraicExpression parseConst(String prefix) {
        StringBuilder data = new StringBuilder(prefix);
        while (Character.isDigit(source.test())) {
            data.append(source.take());
        }
        return new Const(Integer.parseInt(data.toString()));
    }

    private AlgebraicExpression parseUnary(Priority minPriority) throws ParseExpressionException {
        if (minPriority.compare(Priority.UNARY) > 0) {
            throw new ParseExpressionException("Could not parse unary operator because its priority is too low");
        }
        skipSpaces();
        if (source.take('-')) {
            if (Character.isDigit(source.test())) {
                return parseConst("-");
            } else {
                return new CheckedNegate(parseOperand(Priority.UNARY, false));
            }
        } else if (source.take("reverse")) {
            return new CheckedReverse(parseOperand(Priority.UNARY, true));
        } else if (source.take("pow10")) {
            return new CheckedPow10(parseOperand(Priority.UNARY, true));
        } else if (source.take("log10")) {
            return new CheckedLog10(parseOperand(Priority.UNARY, true));
        } else if (source.test('x') || source.test('y') || source.test('z')) {
            String name = String.valueOf(source.take());
            if (Character.isLetterOrDigit(source.test())) {
                throw unknownOperatorException();
            }
            return new Variable(name);
        } else {
            throw unknownOperatorException();
        }
    }

    private record OperationUnit(String operator, Priority priority, BinaryOperator<AlgebraicExpression> constructor) {}
    private static final List<OperationUnit> binaryOperations = List.of(
            new OperationUnit("+", Priority.SUM, CheckedAdd::new),
            new OperationUnit("-", Priority.SUM, CheckedSubtract::new),
            new OperationUnit("*", Priority.MULTIPLY, CheckedMultiply::new),
            new OperationUnit("/", Priority.DIVIDE, CheckedDivide::new),
            new OperationUnit("gcd", Priority.GCD, CheckedGCD::new),
            new OperationUnit("lcm", Priority.LCM, CheckedLCM::new)
    );

    private AlgebraicExpression parseBinary(AlgebraicExpression firstOperand, Priority minPriority) throws ParseExpressionException {
        skipSpaces();
        if (source.eof() || source.test() == ')') {
            return firstOperand;
        }
        for (OperationUnit operation : binaryOperations) {
            if (!source.test(operation.operator)) {
                continue;
            }
            if (minPriority.compare(operation.priority) > 0) {
                return firstOperand;
            }
            source.take(operation.operator);
            boolean isWord = Character.isLetterOrDigit(operation.operator.charAt(operation.operator.length() - 1));
            return operation.constructor.apply(firstOperand, parseOperand(operation.priority.higher(), isWord));
        }
        throw unknownOperatorException();
    }

    private ParseExpressionException unknownOperatorException() {
        return new ParseExpressionException("Unknown operator at position " + source.getPosition() + " found");
    }

    public AlgebraicExpression parseOperand(Priority minPriority, boolean wordOperator) throws ParseExpressionException {
        if (wordOperator && Character.isLetterOrDigit(source.test())) {
            throw unknownOperatorException();
        }
        skipSpaces();
        return parseExpression(minPriority);
    }

    public void skipSpaces() {
        while (Character.isWhitespace(source.test())) {
            source.take();
        }
    }
}
