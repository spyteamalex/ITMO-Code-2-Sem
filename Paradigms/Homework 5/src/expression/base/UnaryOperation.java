package expression.base;

import java.util.Objects;

public abstract class UnaryOperation<Op, Res> implements GenericExpression<Res> {
    protected final GenericExpression<? extends Op> operand;

    public UnaryOperation(GenericExpression<? extends Op> operand) {
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
        UnaryOperation<?, ?> that = (UnaryOperation<?, ?>) obj;
        return Objects.equals(operand, that.operand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operand, getOperatorSymbol());
    }

    @Override
    public Priority getPriority() {
        return Priority.MAX;
    }

    public abstract Res evalImpl(Op op);

    @Override
    public Res eval(int x, int y, int z) {
        return evalImpl(operand.eval(x, y, z));
    }
}
