package expression.base;

import java.util.Objects;

public final class Priority implements Comparable<Priority> {
    public static final Priority MIN = Priority.of(Integer.MIN_VALUE),
            GCD = Priority.equal(MIN),
            LCM = Priority.equal(MIN),
            SUM = Priority.of(200),
            MULTIPLY = Priority.of(300),
            DIVIDE = Priority.equal(MULTIPLY),
            MAX = Priority.of(Integer.MAX_VALUE);

    public final int intValue;

    private Priority(int intValue) {
        this.intValue = intValue;
    }

    public static Priority of(int intValue) {
        return new Priority(intValue);
    }

    public static Priority equal(Priority priority) {
        return of(priority.intValue);
    }

    public Priority higher() {
        if (equals(MAX)) {
            return MAX;
        }
        return of(intValue + 1);
    }

    public Priority lower() {
        if (equals(MIN)) {
            return MIN;
        }
        return of(intValue - 1);
    }

    @Override
    public int compareTo(Priority priority) {
        return Integer.compare(intValue, priority.intValue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Priority priority = (Priority) o;
        return intValue == priority.intValue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(intValue);
    }
}
