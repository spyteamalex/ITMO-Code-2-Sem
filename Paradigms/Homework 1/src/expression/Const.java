package expression;

import java.util.Objects;

public class Const implements AlgebraicExpression {
    private final int value;

    public Const(int value) {
        this.value = value;
    }

    @Override
    public String toMiniString() {
        return toString();
    }

    @Override
    public Priority getPriority() {
        return Priority.VALUE;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        Const that = (Const) obj;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return value;
    }
}
