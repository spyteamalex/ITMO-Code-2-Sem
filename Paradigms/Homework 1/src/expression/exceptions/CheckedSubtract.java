package expression.exceptions;

import expression.AlgebraicExpression;
import expression.Subtract;
import expression.tools.MathTools;

public class CheckedSubtract extends Subtract {
    public CheckedSubtract(AlgebraicExpression leftOperand, AlgebraicExpression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public int calc(int left, int right) {
        return MathTools.exactSubtract(left, right);
    }
}
