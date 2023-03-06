package expression.base;

import java.util.Objects;

public class GenericConst<T> implements GenericExpression<T> {
    private final T value;

    public GenericConst(T value) {
        this.value = value;
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
        return value.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        GenericConst<?> that = (GenericConst<?>) obj;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public T eval(int x, int y, int z) {
        return value;
    }
}
