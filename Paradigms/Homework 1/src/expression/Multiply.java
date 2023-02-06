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
    public int calc(int left, int right) {
        return left * right;
    }
}
