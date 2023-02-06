package expression.exceptions;

import expression.*;
import expression.exceptions.exceptions.OverflowException;
import expression.tools.ErrorTools;
import expression.tools.MathTools;

public class CheckedNegate extends UnaryOperation {

    public CheckedNegate(AlgebraicExpression operand) {
        super(operand);
    }

    @Override
    public Priority getPriority() {
        return Priority.UNARY;
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
