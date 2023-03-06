package expression.base;

import expression.calculators.Summator;

public class GenericAdd<T> extends AssociativeOperation<T> {
    private final Summator<? super T, ? super T, ? extends T> calculator;

    public GenericAdd(GenericExpression<? extends T> leftOperand,
                      GenericExpression<? extends T> rightOperand,
                      Summator<? super T, ? super T, ? extends T> summator) {
        super(leftOperand, rightOperand);
        this.calculator = summator;
    }

    @Override
    protected String getOperatorSymbol() {
        return "+";
    }

    @Override
    public T evalImpl(T left, T right) {
        return calculator.add(left, right);
    }

    @Override
    public Priority getPriority() {
        return Priority.SUM;
    }
}
