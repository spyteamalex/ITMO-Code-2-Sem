package expression;

import expression.base.GenericDivide;
import expression.base.GenericExpression;
import expression.calculators.IntegerCalculator;

public class Divide extends GenericDivide<Integer> implements AlgebraicExpression {
    public Divide(GenericExpression<? extends Integer> leftOperand, GenericExpression<? extends Integer> rightOperand) {
        super(leftOperand, rightOperand, IntegerCalculator.INSTANCE);
    }
}
