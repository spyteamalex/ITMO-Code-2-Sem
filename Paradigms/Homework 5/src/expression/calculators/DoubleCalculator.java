package expression.calculators;

public enum DoubleCalculator implements Calculator<Double> {
    INSTANCE;

    @Override
    public Double to(Integer op) {
        return op.doubleValue();
    }

    @Override
    public Double multiply(Double left, Double right) {
        return left * right;
    }

    @Override
    public Double divide(Double left, Double right) {
        return left / right;
    }

    @Override
    public Double add(Double left, Double right) {
        return left + right;
    }

    @Override
    public Double subtract(Double left, Double right) {
        return left - right;
    }

    @Override
    public Double negate(Double op) {
        return -op;
    }

    @Override
    public Double parse(String str) {
        return Double.valueOf(str);
    }
}
