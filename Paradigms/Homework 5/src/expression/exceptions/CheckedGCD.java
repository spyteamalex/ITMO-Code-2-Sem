package expression.exceptions;

import expression.AlgebraicExpression;
import expression.base.AssociativeOperation;
import expression.base.GenericAdd;
import expression.base.GenericExpression;
import expression.base.Priority;
import expression.tools.MathTools;

public class CheckedGCD extends AssociativeOperation<Integer> implements AlgebraicExpression {

    public CheckedGCD(GenericExpression<? extends Integer> leftOperand, GenericExpression<? extends Integer> rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    protected String getOperatorSymbol() {
        return "gcd";
    }

    @Override
    public Integer evalImpl(Integer left, Integer right) {
        return MathTools.exactGCD(left, right);
    }

    @Override
    public Priority getPriority() {
        return Priority.GCD;
    }
}
