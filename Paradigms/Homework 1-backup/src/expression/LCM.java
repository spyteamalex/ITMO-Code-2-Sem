package expression;

public class LCM extends AssociativeOperation {
    public LCM(AlgebraicExpression leftOperand, AlgebraicExpression rightOperand) {
        super(leftOperand, rightOperand, Priority.LCM);
    }

    @Override
    protected String getOperatorSymbol() {
        return "lcm";
    }
    private int gcd(int a, int b) {
        while (b != 0 && a != 0) {
            int c = a % b;
            a = b;
            b = c;
        }
        return Math.abs(a) + Math.abs(b);
    }

    @Override
    public double evaluate(double x) {
        throw new RuntimeException("Unsupported operation");
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int left = leftOperand.evaluate(x, y, z);
        int right = rightOperand.evaluate(x, y, z);
        if (left == 0 || right == 0) {
            return 0;
        }
        return left / gcd(left, right) * right;
    }
}
