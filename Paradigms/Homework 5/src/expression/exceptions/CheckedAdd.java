package expression.exceptions;

import expression.AlgebraicExpression;
import expression.base.GenericAdd;
import expression.base.GenericExpression;
import expression.calculators.CheckedIntegerCalculator;

public class CheckedAdd extends GenericAdd<Integer> implements AlgebraicExpression {

    public CheckedAdd(GenericExpression<? extends Integer> leftOperand, GenericExpression<? extends Integer> rightOperand) {
        super(leftOperand, rightOperand, CheckedIntegerCalculator.INSTANCE);
    }
}
