package expression.exceptions;

import expression.AlgebraicExpression;
import expression.base.GenericDivide;
import expression.base.GenericExpression;
import expression.calculators.CheckedIntegerCalculator;

public class CheckedDivide extends GenericDivide<Integer> implements AlgebraicExpression {
    public CheckedDivide(GenericExpression<? extends Integer> leftOperand, GenericExpression<? extends Integer> rightOperand) {
        super(leftOperand, rightOperand, CheckedIntegerCalculator.INSTANCE);
    }
}
