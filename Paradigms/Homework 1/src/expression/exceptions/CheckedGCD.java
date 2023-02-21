package expression.exceptions;

import expression.AlgebraicExpression;
import expression.AssociativeOperation;
import expression.tools.MathTools;
import expression.Priority;

public class CheckedGCD extends AssociativeOperation {
    public CheckedGCD(AlgebraicExpression leftOperand, AlgebraicExpression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    protected String getOperatorSymbol() {
        return "gcd";
    }

    @Override
    public int calc(int left, int right) {
        return MathTools.exactGCD(left, right);
    }

    @Override
    public Priority getPriority() {
        return Priority.GCD;
    }
}
