package expression.exceptions;

import expression.AlgebraicExpression;
import expression.UnaryOperation;
import expression.tools.MathTools;

public class CheckedLog10 extends UnaryOperation {

    public CheckedLog10(AlgebraicExpression operand) {
        super(operand);
    }

    @Override
    public int calc(int op) {
        return MathTools.exactLog10(op);
    }

    @Override
    protected String getOperatorSymbol() {
        return "log10";
    }
}
