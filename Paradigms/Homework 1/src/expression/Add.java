package expression;

public class Add extends AssociativeOperation {
    public Add(AlgebraicExpression leftOperand, AlgebraicExpression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    protected String getOperatorSymbol() {
        return "+";
    }

    @Override
    public int calc(int left, int right) {
        return left + right;
    }

    @Override
    public Priority getPriority() {
        return Priority.SUM;
    }
}
