package expression.base;

import expression.calculators.Summator;

public class GenericSubtract<T> extends BinaryOperation<T, T, T> {
    private final Summator<? super T, ? super T, ? extends T> summator;

    public GenericSubtract(GenericExpression<? extends T> leftOperand,
                           GenericExpression<? extends T> rightOperand,
                           Summator<? super T, ? super T, ? extends T> summator) {
        super(leftOperand, rightOperand);
        this.summator = summator;
    }

    @Override
    protected String getOperatorSymbol() {
        return "-";
    }

    @Override
    public Priority getPriority() {
        return Priority.SUM;
    }

    @Override
    public T evalImpl(T left, T right) {
        return summator.subtract(left, right);
    }
}
