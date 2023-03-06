package expression.exceptions;

import expression.AlgebraicExpression;
import expression.base.GenericExpression;
import expression.base.GenericMultiply;
import expression.calculators.CheckedIntegerCalculator;

public class CheckedMultiply extends GenericMultiply<Integer> implements AlgebraicExpression {
    public CheckedMultiply(GenericExpression<? extends Integer> leftOperand, GenericExpression<? extends Integer> rightOperand) {
        super(leftOperand, rightOperand, CheckedIntegerCalculator.INSTANCE);
    }
}
