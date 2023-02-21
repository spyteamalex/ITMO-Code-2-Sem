package expression;

public class Divide extends BinaryOperation {
    public Divide(AlgebraicExpression leftOperand, AlgebraicExpression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    protected String getOperatorSymbol() {
        return "/";
    }

    @Override
    public int calc(int left, int right) {
        return left / right;
    }

    @Override
    public Priority getPriority() {
        return Priority.DIVIDE;
    }
}
