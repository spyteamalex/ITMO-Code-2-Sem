package expression.calculators;

import expression.tools.MathTools;

public enum CheckedIntegerCalculator implements Calculator<Integer> {
    INSTANCE;

    @Override
    public Integer to(Integer op) {
        return op;
    }

    @Override
    public Integer multiply(Integer left, Integer right) {
        return MathTools.exactMultiply(left, right);
    }

    @Override
    public Integer divide(Integer left, Integer right) {
        return MathTools.exactDivide(left, right);
    }

    @Override
    public Integer negate(Integer op) {
        return MathTools.exactNegate(op);
    }

    @Override
    public Integer add(Integer left, Integer right) {
        return MathTools.exactAdd(left, right);
    }

    @Override
    public Integer subtract(Integer left, Integer right) {
        return MathTools.exactSubtract(left, right);
    }

    @Override
    public Integer parse(String str) {
        return Integer.valueOf(str);
    }
}
