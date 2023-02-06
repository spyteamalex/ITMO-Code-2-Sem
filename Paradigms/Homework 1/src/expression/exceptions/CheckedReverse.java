package expression.exceptions;

import expression.*;
import expression.tools.MathTools;

public class CheckedReverse extends UnaryOperation {

    public CheckedReverse(AlgebraicExpression operand) {
        super(operand);
    }

    @Override
    public int calc(int op) {
        return MathTools.exactReverse(op);
    }

    @Override
    public Priority getPriority() {
        return Priority.UNARY;
    }

    @Override
    protected String getOperatorSymbol() {
        return "reverse";
    }
}
