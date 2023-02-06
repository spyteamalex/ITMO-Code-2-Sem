package expression.exceptions;

import expression.AlgebraicExpression;
import expression.AssociativeOperation;
import expression.Multiply;
import expression.Priority;
import expression.tools.MathTools;

public class CheckedMultiply extends Multiply {
    public CheckedMultiply(AlgebraicExpression leftOperand, AlgebraicExpression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public int calc(int left, int right) {
        return MathTools.exactMultiply(left, right);
    }
}
