package expression.base;

import expression.calculators.Negator;

public class GenericNegate<T> extends UnaryOperation<T, T> {

    private final Negator<? super T, ? extends T> negator;

    public GenericNegate(GenericExpression<? extends T> operand, Negator<? super T, ? extends T> negator) {
        super(operand);
        this.negator = negator;
    }

    @Override
    protected String getOperatorSymbol() {
        return "-";
    }

    @Override
    public T evalImpl(T t) {
        return negator.negate(t);
    }
}
