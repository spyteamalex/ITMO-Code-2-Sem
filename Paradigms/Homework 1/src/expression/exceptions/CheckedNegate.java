package expression.exceptions;

import expression.AlgebraicExpression;
import expression.Priority;
import expression.UnaryOperation;
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
