package expression;

public class Add extends AssociativeOperation {
    public Add(AlgebraicExpression leftOperand, AlgebraicExpression rightOperand) {
        super(leftOperand, rightOperand, Priority.SUM);
    }

    @Override
    protected String getOperatorSymbol() {
        return "+";
    }

    @Override
    public double evaluate(double x) {
        return leftOperand.evaluate(x) + rightOperand.evaluate(x);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return leftOperand.evaluate(x, y, z) + rightOperand.evaluate(x, y, z);
    }
}
