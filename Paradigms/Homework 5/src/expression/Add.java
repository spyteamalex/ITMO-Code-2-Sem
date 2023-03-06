package expression;

import expression.base.GenericAdd;
import expression.base.GenericExpression;
import expression.calculators.IntegerCalculator;

public class Add extends GenericAdd<Integer> implements AlgebraicExpression {
    public Add(GenericExpression<? extends Integer> leftOperand, GenericExpression<? extends Integer> rightOperand) {
        super(leftOperand, rightOperand, IntegerCalculator.INSTANCE);
    }
}
