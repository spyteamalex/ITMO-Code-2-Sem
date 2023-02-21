package expression.exceptions;

import expression.AlgebraicExpression;
import expression.UnaryOperation;
import expression.tools.MathTools;

public class CheckedNegate extends UnaryOperation {

    public CheckedNegate(AlgebraicExpression operand) {
        super(operand);
    }

    @Override
    protected String getOperatorSymbol() {
        return "-";
    }

    @Override
    public int calc(int op) {
        return MathTools.exactNegate(op);
    }
}
