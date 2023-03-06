package expression.exceptions;

import expression.AlgebraicExpression;
import expression.base.AssociativeOperation;
import expression.base.GenericExpression;
import expression.base.Priority;
import expression.tools.MathTools;

public class CheckedLCM extends AssociativeOperation<Integer> implements AlgebraicExpression {
    public CheckedLCM(GenericExpression<? extends Integer> leftOperand, GenericExpression<? extends Integer> rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    protected String getOperatorSymbol() {
        return "lcm";
    }

    @Override
    public Integer evalImpl(Integer left, Integer right) {
        return MathTools.exactLCM(left, right);
    }

    @Override
    public Priority getPriority() {
        return Priority.LCM;
    }
}
