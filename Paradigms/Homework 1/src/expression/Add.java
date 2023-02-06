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
    public int calc(int left, int right) {
        return left + right;
    }
}
