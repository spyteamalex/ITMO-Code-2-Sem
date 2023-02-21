package expression;

import java.util.Objects;

public abstract class BinaryOperation implements AlgebraicExpression {
    protected final AlgebraicExpression leftOperand;
    protected final AlgebraicExpression rightOperand;

    protected BinaryOperation(final AlgebraicExpression leftOperand, final AlgebraicExpression rightOperand) {
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

    private StringBuilder addOperand(StringBuilder stringBuilder, AlgebraicExpression expression, boolean brackets) {
        if (brackets) {
            stringBuilder.append("(").append(expression.toMiniString()).append(")");
        } else {
            stringBuilder.append(expression.toMiniString());
        }
        return stringBuilder;
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
        final BinaryOperation that = (BinaryOperation) obj;
        return Objects.equals(leftOperand, that.leftOperand) && Objects.equals(rightOperand, that.rightOperand);
    }

    public abstract int calc(int left, int right);

    @Override
    public int evaluate(final int x, final int y, final int z) {
        return calc(leftOperand.evaluate(x, y, z), rightOperand.evaluate(x, y, z));
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPriority(), leftOperand, rightOperand, getOperatorSymbol());
    }
}
