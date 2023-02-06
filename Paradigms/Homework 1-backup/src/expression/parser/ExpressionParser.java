package expression.parser;

import expression.*;

public class ExpressionParser implements TripleParser {
    public ExpressionParser() {
        super();
    }

    private StringReader source;

    public AlgebraicExpression parse(String expression) {
        source = new StringReader(new StringSource(expression));
        return parseExpression(Priority.ZERO);
    }

    private AlgebraicExpression parseExpression(Priority minPriority) {
        AlgebraicExpression firstOperand;
        skipSpaces();
        Tools.require(!source.eof(), "Unexpected end of file");
        if (source.take('(')) {
            firstOperand = parseExpression(Priority.ZERO);
            source.take();
        } else if (Character.isDigit(source.get())) {
            firstOperand = parseConst();
        } else if (source.get() == 'x' || source.get() == 'y' || source.get() == 'z') {
            firstOperand = parseVariable();
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
        while (Character.isDigit(source.get())) {
            data.append(source.take());
        }
        return new Const(Integer.parseInt(data.toString()));
    }

    private AlgebraicExpression parseVariable() {
        StringBuilder data = new StringBuilder();
        while (Character.isLetter(source.get())) {
            data.append(source.take());
        }
        return new Variable(data.toString());
    }

    private AlgebraicExpression parseUnary(Priority minPriority) {
        Tools.require(minPriority.compare(Priority.UNARY) <= 0, "Could not parse unary operator because its priority is too low");
        skipSpaces();
        String operator = null;
        if (source.take('-')) {
            if (Character.isDigit(source.get())) {
                return parseConst("-");
            } else {
                operator = "-";
            }
        } else if (source.take("reverse")) {
            operator = "reverse";
        } else {
            Tools.raiseError("Unknown operator starts with " + source.get() + " found");
        }
        skipSpaces();
        AlgebraicExpression operand = parseExpression(Priority.UNARY);
        return switch (operator) {
            case "-" -> new UnaryMinus(operand);
            case "reverse" -> new UnaryReverse(operand);
            default -> null;
        };
    }

    private AlgebraicExpression parseBinary(AlgebraicExpression firstOperand, Priority minPriority) {
        skipSpaces();
        if (source.eof() || source.get() == ')') {
            return firstOperand;
        }
        Priority priority = null;
        String cmd = null;
        if (source.test('+')) {
            priority = Priority.SUM;
            cmd = "+";
        } else if (source.test('-')) {
            priority = Priority.SUM;
            cmd = "-";
        } else if (source.test('*')) {
            priority = Priority.MULTIPLY;
            cmd = "*";
        } else if (source.test('/')) {
            priority = Priority.DIVIDE;
            cmd = "/";
        } else if (source.test("gcd")) {
            priority = Priority.GCD;
            cmd = "gcd";
        } else if (source.test("lcm")) {
            priority = Priority.LCM;
            cmd = "lcm";
        } else {
            Tools.raiseError("Unknown operator starts with " + source.get() + " found");
        }
        if (minPriority.compare(priority) > 0) {
            return firstOperand;
        } else {
            source.take(cmd);
            AlgebraicExpression secondOperand = parseExpression(priority.higher());
            return switch (cmd) {
                case "+" -> new Add(firstOperand, secondOperand);
                case "-" -> new Subtract(firstOperand, secondOperand);
                case "*" -> new Multiply(firstOperand, secondOperand);
                case "/" -> new Divide(firstOperand, secondOperand);
                case "gcd" -> new GCD(firstOperand, secondOperand);
                case "lcm" -> new LCM(firstOperand, secondOperand);
                default -> null;
            };
        }
    }

    public void skipSpaces() {
        while (Character.isWhitespace(source.get())) {
            source.take();
        }
    }
}
