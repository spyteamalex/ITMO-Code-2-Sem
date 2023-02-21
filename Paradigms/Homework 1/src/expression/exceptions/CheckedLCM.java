package expression.exceptions;

import expression.AlgebraicExpression;
import expression.AssociativeOperation;
import expression.Priority;
import expression.tools.MathTools;

public class CheckedLCM extends AssociativeOperation {
    public CheckedLCM(AlgebraicExpression leftOperand, AlgebraicExpression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    protected String getOperatorSymbol() {
        return "lcm";
    }

    @Override
    public int calc(int left, int right) {
        return MathTools.exactLCM(left, right);
    }

    @Override
    public Priority getPriority() {
        return Priority.LCM;
    }
}
