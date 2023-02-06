package expression.exceptions;

import expression.AlgebraicExpression;
import expression.AssociativeOperation;
import expression.exceptions.exceptions.OverflowException;
import expression.tools.ErrorTools;
import expression.tools.MathTools;
import expression.Priority;

public class CheckedLCM extends AssociativeOperation {
    public CheckedLCM(AlgebraicExpression leftOperand, AlgebraicExpression rightOperand) {
        super(leftOperand, rightOperand, Priority.LCM);
    }

    @Override
    protected String getOperatorSymbol() {
        return "lcm";
    }

    @Override
    public int calc(int left, int right) {
        return MathTools.exactLCM(left, right);
    }
}
