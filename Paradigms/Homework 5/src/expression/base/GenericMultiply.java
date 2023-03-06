package expression.base;

import expression.calculators.Multiplicator;

public class GenericMultiply<T> extends AssociativeOperation<T> {
    private final Multiplicator<? super T, ? super T, ? extends T> multiplicator;

    public GenericMultiply(GenericExpression<? extends T> leftOperand,
                           GenericExpression<? extends T> rightOperand,
                           Multiplicator<? super T, ? super T, ? extends T> multiplicator) {
        super(leftOperand, rightOperand);
        this.multiplicator = multiplicator;
    }

    @Override
    protected String getOperatorSymbol() {
        return "*";
    }

    @Override
    public Priority getPriority() {
        return Priority.MULTIPLY;
    }

    @Override
    public T evalImpl(T left, T right) {
        return multiplicator.multiply(left, right);
    }
}
