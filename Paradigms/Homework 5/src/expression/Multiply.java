package expression;

import expression.base.GenericExpression;
import expression.base.GenericMultiply;
import expression.calculators.IntegerCalculator;

public class Multiply extends GenericMultiply<Integer> implements AlgebraicExpression {
    public Multiply(GenericExpression<? extends Integer> leftOperand, GenericExpression<? extends Integer> rightOperand) {
        super(leftOperand, rightOperand, IntegerCalculator.INSTANCE);
    }
}
