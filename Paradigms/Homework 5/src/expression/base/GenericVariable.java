package expression.base;

import expression.calculators.Converter;

import java.util.Objects;

public class GenericVariable<T> implements GenericExpression<T> {
    private final String symbol;
    private final Converter<Integer, ? extends T> converter;

    public GenericVariable(String symbol, Converter<Integer, ? extends T> converter) {
        this.symbol = symbol;
        this.converter = converter;
    }

    @Override
    public String toMiniString() {
        return toString();
    }

    @Override
    public Priority getPriority() {
        return Priority.MAX;
    }

    @Override
    public String toString() {
        return symbol;
    }

    @Override
    public boolean equals(Object that) {
        if (that == null || getClass() != that.getClass()) return false;
        GenericVariable<?> thatConst = (GenericVariable<?>) that;
        return Objects.equals(symbol, thatConst.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol);
    }

    @Override
    public T eval(int x, int y, int z) {
        return converter.to(switch (symbol) {
            case "Y", "y" -> y;
            case "Z", "z" -> z;
            default -> x;
        });
    }
}
