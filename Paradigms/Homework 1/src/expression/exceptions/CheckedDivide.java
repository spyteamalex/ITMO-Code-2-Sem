package expression.exceptions;

import expression.AlgebraicExpression;
import expression.Divide;
import expression.tools.MathTools;

public class CheckedDivide extends Divide {
    public CheckedDivide(AlgebraicExpression leftOperand, AlgebraicExpression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public int calc(int left, int right) {
        return MathTools.exactDivide(left, right);
    }
}
