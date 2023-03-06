package expression.parser;

import expression.base.GenericExpression;
import expression.base.Priority;
import expression.exceptions.StringSource;
import expression.exceptions.TokenReader;
import expression.exceptions.exceptions.ParseExpressionException;

import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;

public class GenericParser<E extends GenericExpression<?>> {
    private TokenReader source;
    private final Set<String> variables;

    public record BinaryOpUnit<E>(Priority priority,
                                  BiFunction<
                                          ? super E,
                                          ? super E,
                                          ? extends E> constructor) {
    }

    private final Map<String, BinaryOpUnit<E>> binaryOperations;

    private final Map<String, Function<? super E, ? extends E>> unaryOperations;

    private final Function<String, ? extends E> constCreator;
    private final Function<String, ? extends E> variableCreator;

    public GenericParser(Map<String, BinaryOpUnit<E>> binaryOperations,
                         Map<String, Function<? super E, ? extends E>> unaryOperations,
                         Set<String> variables,
                         Function<String, ? extends E> constCreator,
                         Function<String, ? extends E> variableCreator) {
        this.binaryOperations = binaryOperations;
        this.unaryOperations = unaryOperations;
        this.variables = variables;
        this.constCreator = constCreator;
        this.variableCreator = variableCreator;
    }

    public E parse(String expression) throws ParseExpressionException {
        source = new TokenReader(new StringSource(expression));
        E result = parseClause(Priority.MIN);
        if (!source.eof()) {
            throw new ParseExpressionException("Unexpected data at position " + (source.getPosition() + 1) + " found: " + source.take(), source.getPosition());
        }
        return result;
    }

    public E parseClause(Priority minPriority) throws ParseExpressionException {
        if (source.eof()) {
            throw new ParseExpressionException("Unexpected end of data at position " + (source.getPosition() + 1), source.getPosition());
        }
        if (source.test(")")) {
            throw new ParseExpressionException("Unexpected end of clause at position " + (source.getPosition() + 1), source.getPosition());
        }
        E firstOperand = parseOperand();
        while (!source.eof() && !source.test(")")) {
            E buffer = parseBinary(firstOperand, minPriority);
            if (buffer == null) {
                break;
            }
            firstOperand = buffer;
        }
        return firstOperand;
    }

    private E parseOperand() throws ParseExpressionException {
        E firstOperand;
        if (source.take("(")) {
            firstOperand = parseClause(Priority.MIN);
            if (!source.take(")")) {
                throw new ParseExpressionException("End of clause expected at position " + (source.getPosition() + 1) + ", but not found", source.getPosition());
            }
        } else if (Character.isDigit(source.get().charAt(0))) {
            firstOperand = parseConst();
        } else {
            firstOperand = parseUnary();
        }
        return firstOperand;
    }

    private E parseConst() {
        return parseConst("");
    }

    private E parseConst(String prefix) {
        return constCreator.apply(prefix + source.take());
    }

    private E parseUnary() throws ParseExpressionException {
        int position = source.getPosition();
        String token = source.take();
        if (variables.contains(token)) {
            return variableCreator.apply(token);
        }
        if (token.equals("-") && (source.getPosition() - position) <= token.length() && Character.isDigit(source.get().charAt(0))) {
            return parseConst("-");
        }
        var unit = unaryOperations.get(token);
        if (unit == null) {
            throw unknownOperatorException(position, token, "unary");
        }
        return unit.apply(parseClause(Priority.MAX));
    }

    private E parseBinary(E firstOperand, Priority minPriority) throws ParseExpressionException {
        var unit = binaryOperations.get(source.get());
        if (unit == null) {
            throw unknownOperatorException(source.getPosition(), source.get(), "binary");
        }
        if (minPriority.compareTo(unit.priority) > 0) {
            return null;
        }
        source.take();
        return unit.constructor.apply(firstOperand, parseClause(unit.priority.higher()));
    }

    private static ParseExpressionException unknownOperatorException(int position, String operator, String operatorType) {
        return new ParseExpressionException("Unknown " + operatorType + " operator found at position " + (position + 1) + ": " + operator, position);
    }
}
