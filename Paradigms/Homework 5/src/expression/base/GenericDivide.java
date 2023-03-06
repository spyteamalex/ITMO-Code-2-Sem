package expression.base;

import expression.calculators.Multiplicator;

public class GenericDivide<T> extends BinaryOperation<T, T, T> {
    private final Multiplicator<? super T, ? super T, ? extends T> multiplicator;

    public GenericDivide(GenericExpression<? extends T> leftOperand,
                         GenericExpression<? extends T> rightOperand,
                         Multiplicator<? super T, ? super T, ? extends T> multiplicator) {
        super(leftOperand, rightOperand);
        this.multiplicator = multiplicator;
    }

    @Override
    protected String getOperatorSymbol() {
        return "/";
    }

    @Override
    public Priority getPriority() {
        return Priority.DIVIDE;
    }

    @Override
    public T evalImpl(T left, T right) {
        return multiplicator.divide(left, right);
    }
}
