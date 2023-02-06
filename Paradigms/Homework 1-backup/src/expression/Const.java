package expression;

import java.util.Objects;

public class Const implements AlgebraicExpression {
    private final double valueDouble;
    private final int valueInt;
    private final boolean isInt;

    public Const(double value) {
        this.valueInt = (int) value;
        this.valueDouble = value;
        isInt = false;
    }

    public Const(int value) {
        this.valueInt = value;
        this.valueDouble = value;
        isInt = true;
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
        return isInt ? Integer.toString(valueInt) : Double.toString(valueDouble);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        Const that = (Const) obj;
        return valueInt == that.valueInt && valueDouble == that.valueDouble;
    }

    @Override
    public int hashCode() {
        return Objects.hash(valueInt, valueDouble);
    }

    @Override
    public double evaluate(double x) {
        return valueDouble;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return valueInt;
    }
}
