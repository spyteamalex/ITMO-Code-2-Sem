package expression.exceptions;

import expression.AlgebraicExpression;
import expression.base.GenericExpression;
import expression.base.GenericSubtract;
import expression.calculators.CheckedIntegerCalculator;

public class CheckedSubtract extends GenericSubtract<Integer> implements AlgebraicExpression {
    public CheckedSubtract(GenericExpression<? extends Integer> leftOperand, GenericExpression<? extends Integer> rightOperand) {
        super(leftOperand, rightOperand, CheckedIntegerCalculator.INSTANCE);
    }
}
