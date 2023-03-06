package expression.base;

import java.util.Objects;

public abstract class BinaryOperation<Left, Right, Res> implements GenericExpression<Res> {
    protected final GenericExpression<? extends Left> leftOperand;
    protected final GenericExpression<? extends Right> rightOperand;

    protected BinaryOperation(final GenericExpression<? extends Left> leftOperand,
                              final GenericExpression<? extends Right> rightOperand) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    @Override
    public String toMiniString() {
        final StringBuilder stringBuilder = new StringBuilder();
        addOperand(stringBuilder, leftOperand, leftNeedsBrackets());
        stringBuilder.append(" ").append(getOperatorSymbol()).append(" ");
        addOperand(stringBuilder, rightOperand, rightNeedsBrackets());
        return stringBuilder.toString();
    }

    private void addOperand(StringBuilder stringBuilder, GenericExpression<?> expression, boolean brackets) {
        if (brackets) {
            stringBuilder.append("(").append(expression.toMiniString()).append(")");
        } else {
            stringBuilder.append(expression.toMiniString());
        }
    }

    protected boolean leftNeedsBrackets() {
        return leftOperand.getPriority().compareTo(getPriority()) < 0;
    }

    protected boolean rightNeedsBrackets() {
        return rightOperand.getPriority().compareTo(getPriority()) <= 0;
    }

    @Override
    public String toString() {
        return "(" + leftOperand + " " + getOperatorSymbol() + " " + rightOperand + ")";
    }

    protected abstract String getOperatorSymbol();

    @Override
    public boolean equals(final Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        final BinaryOperation<?, ?, ?> that = (BinaryOperation<?, ?, ?>) obj;
        return Objects.equals(leftOperand, that.leftOperand) && Objects.equals(rightOperand, that.rightOperand);
    }

    public abstract Res evalImpl(Left left, Right right);

    @Override
    public Res eval(final int x, final int y, final int z) {
        return evalImpl(leftOperand.eval(x, y, z), rightOperand.eval(x, y, z));
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPriority(), leftOperand, rightOperand, getOperatorSymbol());
    }
}
