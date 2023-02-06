package expression;

public class GCD extends AssociativeOperation {
    public GCD(AlgebraicExpression leftOperand, AlgebraicExpression rightOperand) {
        super(leftOperand, rightOperand, Priority.GCD);
    }

    @Override
    protected String getOperatorSymbol() {
        return "gcd";
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
        return gcd(leftOperand.evaluate(x, y, z), rightOperand.evaluate(x, y, z));
    }
}
