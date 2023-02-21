package expression;

import java.util.Objects;

public abstract class UnaryOperation implements AlgebraicExpression {
    protected final AlgebraicExpression operand;

    public UnaryOperation(AlgebraicExpression operand) {
        this.operand = operand;
    }

    @Override
    public String toMiniString() {
        if (operand.getPriority().compareTo(getPriority()) >= 0) {
            return getOperatorSymbol() + " " + operand.toMiniString();
        }
        return getOperatorSymbol() + "(" + operand.toMiniString() + ")";
    }

    protected abstract String getOperatorSymbol();

    @Override
    public String toString() {
        return getOperatorSymbol() + "(" + operand.toString() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        UnaryOperation that = (UnaryOperation) obj;
        return Objects.equals(operand, that.operand);
    }

    public abstract int calc(int op);

    @Override
    public int evaluate(int x, int y, int z) {
        return calc(operand.evaluate(x, y, z));
    }

    @Override
    public int hashCode() {
        return Objects.hash(operand, getOperatorSymbol());
    }

    @Override
    public Priority getPriority() {
        return Priority.MAX;
    }
}
