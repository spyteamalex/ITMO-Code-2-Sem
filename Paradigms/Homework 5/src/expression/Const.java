package expression;

import expression.base.GenericConst;

public class Const extends GenericConst<Integer> implements AlgebraicExpression {
    public Const(int value) {
        super(value);
    }
}
