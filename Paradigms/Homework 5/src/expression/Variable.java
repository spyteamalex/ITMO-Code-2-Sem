package expression;

import expression.base.GenericVariable;
import expression.calculators.IntegerCalculator;

public class Variable extends GenericVariable<Integer> implements AlgebraicExpression {
    public Variable(String symbol) {
        super(symbol, IntegerCalculator.INSTANCE);
    }
}
