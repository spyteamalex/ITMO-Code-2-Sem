package expression;

public class UnaryMinus extends UnaryOperation {

    public UnaryMinus(AlgebraicExpression operand) {
        super(operand);
    }

    @Override
    public double evaluate(double x) {
        return -operand.evaluate(x);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return -operand.evaluate(x, y, z);
    }

    @Override
    public Priority getPriority() {
        return Priority.UNARY;
    }

    @Override
    protected String getOperatorSymbol() {
        return "-";
    }
}
