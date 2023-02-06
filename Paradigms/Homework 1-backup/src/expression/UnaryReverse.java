package expression;

public class UnaryReverse extends UnaryOperation {

    public UnaryReverse(AlgebraicExpression operand) {
        super(operand);
    }

    private int reverse(int value) {
        int result = 0;
        while (value != 0) {
            result *= 10;
            result += (value % 10);
            value /= 10;
        }
        return result;
    }

    @Override
    public double evaluate(double x) {
        throw new RuntimeException("Unsupported operation");
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return reverse(operand.evaluate(x, y, z));
    }

    @Override
    public Priority getPriority() {
        return Priority.UNARY;
    }

    @Override
    protected String getOperatorSymbol() {
        return "reverse";
    }
}
