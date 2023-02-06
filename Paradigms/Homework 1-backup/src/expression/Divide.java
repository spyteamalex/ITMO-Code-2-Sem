package expression;

public class Divide extends BinaryOperation {
    public Divide(AlgebraicExpression leftOperand, AlgebraicExpression rightOperand) {
        super(leftOperand, rightOperand, Priority.DIVIDE);
    }

    @Override
    protected String getOperatorSymbol() {
        return "/";
    }

    @Override
    public double evaluate(double x) {
        return leftOperand.evaluate(x) / rightOperand.evaluate(x);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return leftOperand.evaluate(x, y, z) / rightOperand.evaluate(x, y, z);
    }
}
