package expression.exceptions;

import expression.AlgebraicExpression;
import expression.base.GenericExpression;
import expression.base.UnaryOperation;
import expression.tools.MathTools;

public class CheckedLog10 extends UnaryOperation<Integer, Integer> implements AlgebraicExpression {

    public CheckedLog10(GenericExpression<? extends Integer> operand) {
        super(operand);
    }


    @Override
    protected String getOperatorSymbol() {
        return "log10";
    }

    @Override
    public Integer evalImpl(Integer op) {
        return MathTools.exactLog10(op);
    }
}
