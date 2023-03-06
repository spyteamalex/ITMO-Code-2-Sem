package expression;

import expression.base.GenericExpression;

public interface AlgebraicExpression extends GenericExpression<Integer>, TripleExpression {
    @Override
    default int evaluate(int x, int y, int z) {
        return eval(x, y, z);
    }
}
