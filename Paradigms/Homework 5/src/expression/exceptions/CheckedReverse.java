package expression.exceptions;

import expression.AlgebraicExpression;
import expression.base.GenericExpression;
import expression.base.UnaryOperation;
import expression.tools.MathTools;

public class CheckedReverse extends UnaryOperation<Integer, Integer> implements AlgebraicExpression {

    public CheckedReverse(GenericExpression<? extends Integer> operand) {
        super(operand);
    }

    @Override
    public Integer evalImpl(Integer op) {
        return MathTools.exactReverse(op);
    }

    @Override
    protected String getOperatorSymbol() {
        return "reverse";
    }
}
