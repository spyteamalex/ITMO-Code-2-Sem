package expression.exceptions;

import expression.AlgebraicExpression;
import expression.base.GenericExpression;
import expression.base.GenericNegate;
import expression.calculators.CheckedIntegerCalculator;

public class CheckedNegate extends GenericNegate<Integer> implements AlgebraicExpression {

    public CheckedNegate(GenericExpression<? extends Integer> operand) {
        super(operand, CheckedIntegerCalculator.INSTANCE);
    }

    @Override
    protected String getOperatorSymbol() {
        return "-";
    }
}
