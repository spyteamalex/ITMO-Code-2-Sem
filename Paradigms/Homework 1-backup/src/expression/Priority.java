package expression;

public enum Priority {
    ZERO(0),
    GCD(1),
    LCM(1),
    SUM(2),

    MULTIPLY(3),
    DIVIDE(3),
    UNARY(4),
    VALUE(5),
    INFINITY(6);

    private final int prior;
    Priority(int prior) {
        this.prior = prior;
    }

    public int compare(Priority that) {
        return Integer.compare(prior, that.prior);
    }

    public Priority higher() {
        return switch (this) {
            case ZERO -> GCD;
            case GCD, LCM -> SUM;
            case SUM -> MULTIPLY;
            case MULTIPLY, DIVIDE -> UNARY;
            case UNARY -> VALUE;
            case VALUE, INFINITY -> INFINITY;
        };
    }
}
