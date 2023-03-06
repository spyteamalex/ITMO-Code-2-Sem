package expression;

import expression.base.GenericExpression;
import expression.base.GenericSubtract;
import expression.calculators.IntegerCalculator;

public class Subtract extends GenericSubtract<Integer> implements AlgebraicExpression {
    public Subtract(GenericExpression<? extends Integer> leftOperand, GenericExpression<? extends Integer> rightOperand) {
        super(leftOperand, rightOperand, IntegerCalculator.INSTANCE);
    }
}
