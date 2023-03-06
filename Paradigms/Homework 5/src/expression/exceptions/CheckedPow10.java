package expression.exceptions;

import expression.AlgebraicExpression;
import expression.base.GenericExpression;
import expression.base.UnaryOperation;
import expression.tools.MathTools;

public class CheckedPow10 extends UnaryOperation<Integer, Integer> implements AlgebraicExpression {

    public CheckedPow10(GenericExpression<? extends Integer> operand) {
        super(operand);
    }

    @Override
    public Integer evalImpl(Integer op) {
        return MathTools.exactPow10(op);
    }

    @Override
    protected String getOperatorSymbol() {
        return "pow10";
    }
}
