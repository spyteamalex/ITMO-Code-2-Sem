package expression.calculators;

public enum IntegerCalculator implements Calculator<Integer> {
    INSTANCE;

    @Override
    public Integer to(Integer op) {
        return op;
    }

    @Override
    public Integer multiply(Integer left, Integer right) {
        return left * right;
    }

    @Override
    public Integer divide(Integer left, Integer right) {
        return left / right;
    }

    @Override
    public Integer add(Integer left, Integer right) {
        return left + right;
    }

    @Override
    public Integer subtract(Integer left, Integer right) {
        return left - right;
    }

    @Override
    public Integer negate(Integer op) {
        return -op;
    }

    @Override
    public Integer parse(String str) {
        return Integer.valueOf(str);
    }
}
