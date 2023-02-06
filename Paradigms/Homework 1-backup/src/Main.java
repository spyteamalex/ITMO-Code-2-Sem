import expression.*;
import expression.parser.ExpressionParser;
import expression.parser.StringSource;

public class Main {
    public static void main(String[] args) {
//        if (args.length == 0) {
//            System.err.println("No x given");
//            return;
//        }
//        int value;
//        try {
//            value = Integer.parseInt(args[0]);
//        } catch (NumberFormatException e) {
//            System.err.println("Given argument is not an integer");
//            e.printStackTrace(System.err);
//            return;
//        }
//        Variable var = new Variable("x");
        AlgebraicExpression expression = new GCD(new Const(10),new GCD(new Const(20), new Const(5)));
//        System.out.println(expression.toMiniString());
//        System.out.println(expression.evaluate(value));
//        System.out.println(Integer.parseInt("2147483648"));
        System.out.println(new ExpressionParser().parse("xgcdy"));
    }
}