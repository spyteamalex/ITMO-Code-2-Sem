package expression.calculators;

import java.math.BigInteger;

public enum BigIntCalculator implements Calculator<BigInteger> {
    INSTANCE;

    @Override
    public BigInteger to(Integer op) {
        return BigInteger.valueOf(op);
    }

    @Override
    public BigInteger multiply(BigInteger left, BigInteger right) {
        return left.multiply(right);
    }

    @Override
    public BigInteger divide(BigInteger left, BigInteger right) {
        return left.divide(right);
    }

    @Override
    public BigInteger add(BigInteger left, BigInteger right) {
        return left.add(right);
    }

    @Override
    public BigInteger subtract(BigInteger left, BigInteger right) {
        return left.subtract(right);
    }

    @Override
    public BigInteger negate(BigInteger op) {
        return op.negate();
    }

    @Override
    public BigInteger parse(String str) {
        return new BigInteger(str);
    }
}
