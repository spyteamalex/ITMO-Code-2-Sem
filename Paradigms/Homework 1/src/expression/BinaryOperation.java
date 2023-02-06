package expression;

import java.util.Objects;

public abstract class BinaryOperation implements AlgebraicExpression {

    protected final Priority priority;
    protected final AlgebraicExpression leftOperand;
    protected final AlgebraicExpression rightOperand;

    protected BinaryOperation(AlgebraicExpression leftOperand, AlgebraicExpression rightOperand, Priority priority) {
        this.priority = priority;
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    @Override
    public String toMiniString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (leftNeedsBrackets()) {
            stringBuilder.append("(").append(leftOperand.toMiniString()).append(")");
        } else {
            stringBuilder.append(leftOperand.toMiniString());
        }
        stringBuilder.append(" ").append(getOperatorSymbol()).append(" ");
        if (rightNeedsBrackets()) {
            stringBuilder.append("(").append(rightOperand.toMiniString()).append(")");
        } else {
            stringBuilder.append(rightOperand.toMiniString());
        }
        return stringBuilder.toString();
    }

    protected boolean leftNeedsBrackets() {
        return leftOperand.getPriority().compare(getPriority()) < 0;
    }

    protected boolean rightNeedsBrackets() {
        return rightOperand.getPriority().compare(getPriority()) <= 0;
    }

    @Override
    public String toString() {
        return "(" + leftOperand + " " + getOperatorSymbol() + " " + rightOperand + ")";
    }

    protected abstract String getOperatorSymbol();

    @Override
    public Priority getPriority() {
        return priority;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        BinaryOperation that = (BinaryOperation) obj;
        return Objects.equals(leftOperand, that.leftOperand) && Objects.equals(rightOperand, that.rightOperand);
    }

    public abstract int calc(int left, int right);

    @Override
    public int evaluate(int x, int y, int z) {
        return calc(leftOperand.evaluate(x, y, z), rightOperand.evaluate(x, y, z));
    }

    @Override
    public int hashCode() {
        return Objects.hash(priority, leftOperand, rightOperand, getOperatorSymbol());
    }
}
