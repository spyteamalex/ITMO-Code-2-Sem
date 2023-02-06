package expression.exceptions;

import expression.Add;
import expression.AlgebraicExpression;
import expression.tools.MathTools;

public class CheckedAdd extends Add {
    public CheckedAdd(AlgebraicExpression leftOperand, AlgebraicExpression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public int calc(int left, int right) {
        return MathTools.exactSum(left, right);
    }
}
