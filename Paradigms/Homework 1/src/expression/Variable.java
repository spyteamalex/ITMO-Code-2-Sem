package expression;

import java.util.Objects;

public class Variable implements AlgebraicExpression {
    private final String symbol;

    public Variable(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public int evaluate(int var) {
        return var;
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
        return symbol;
    }

    @Override
    public boolean equals(Object that) {
        if (that == null || getClass() != that.getClass()) return false;
        Variable thatConst = (Variable) that;
        return Objects.equals(symbol, thatConst.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return switch (symbol) {
            case "Y", "y" -> y;
            case "Z", "z" -> z;
            default -> x;
        };
    }
}
