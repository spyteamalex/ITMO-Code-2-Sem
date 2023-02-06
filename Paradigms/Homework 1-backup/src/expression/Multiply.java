package expression;

public class Multiply extends AssociativeOperation {
    public Multiply(AlgebraicExpression leftOperand, AlgebraicExpression rightOperand) {
        super(leftOperand, rightOperand, Priority.MULTIPLY);
    }

    @Override
    protected String getOperatorSymbol() {
        return "*";
    }

    @Override
    public double evaluate(double param) {
        return leftOperand.evaluate(param) * rightOperand.evaluate(param);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return leftOperand.evaluate(x, y, z) * rightOperand.evaluate(x, y, z);
    }
}
