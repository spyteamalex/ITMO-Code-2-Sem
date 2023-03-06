package expression.base;

public abstract class AssociativeOperation<T> extends BinaryOperation<T, T, T> {
    protected AssociativeOperation(GenericExpression<? extends T> leftOperand, GenericExpression<? extends T> rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    protected boolean rightNeedsBrackets() {
        return super.rightNeedsBrackets() && rightOperand.getPriority() != getPriority();
    }
}
