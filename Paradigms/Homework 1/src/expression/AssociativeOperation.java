package expression;

public abstract class AssociativeOperation extends BinaryOperation {
    protected AssociativeOperation(AlgebraicExpression leftOperand, AlgebraicExpression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    protected boolean rightNeedsBrackets() {
        return super.rightNeedsBrackets() && rightOperand.getPriority() != getPriority();
    }
}
