package expression;

public abstract class AssociativeOperation extends BinaryOperation {
    protected AssociativeOperation(AlgebraicExpression leftOperand, AlgebraicExpression rightOperand, Priority priority) {
        super(leftOperand, rightOperand, priority);
    }

    @Override
    protected boolean rightNeedsBrackets() {
        return super.rightNeedsBrackets() && rightOperand.getPriority() != getPriority();
    }
}
