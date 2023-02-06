package expression;

public class Subtract extends BinaryOperation {
    public Subtract(AlgebraicExpression leftOperand, AlgebraicExpression rightOperand) {
        super(leftOperand, rightOperand, Priority.SUM);
    }

    @Override
    protected String getOperatorSymbol() {
        return "-";
    }

    @Override
    public double evaluate(double param) {
        return leftOperand.evaluate(param) - rightOperand.evaluate(param);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return leftOperand.evaluate(x, y, z) - rightOperand.evaluate(x, y, z);
    }
}
