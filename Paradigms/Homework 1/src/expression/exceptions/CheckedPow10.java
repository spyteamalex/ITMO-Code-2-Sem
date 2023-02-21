package expression.exceptions;

import expression.AlgebraicExpression;
import expression.UnaryOperation;
import expression.tools.MathTools;

public class CheckedPow10 extends UnaryOperation {

    public CheckedPow10(AlgebraicExpression operand) {
        super(operand);
    }

    @Override
    public int calc(int op) {
        return MathTools.exactPow10(op);
    }

    @Override
    protected String getOperatorSymbol() {
        return "pow10";
    }
}
