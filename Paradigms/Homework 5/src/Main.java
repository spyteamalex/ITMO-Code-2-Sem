
import expression.AlgebraicExpression;
import expression.exceptions.ExpressionParser;
import expression.exceptions.exceptions.ParseExpressionException;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String expressionString;
        try {
            expressionString = scanner.nextLine();
        } catch (NoSuchElementException exception) {
            System.err.println("No expression was entered");
            return;
        }
        ExpressionParser parser = new ExpressionParser();
        AlgebraicExpression expression;
        try {
            expression = parser.parse(expressionString);
        } catch (ParseExpressionException e) {
            System.err.println("Parse error: " + e.getMessage());
            return;
        }
        System.out.println("Parsed: " + expression);
        System.out.printf("%10s %10s %10s %10s\n", "x", "y", "z", "f");
        while (true) {
            int x, y, z;
            try {
                System.out.println("Enter x");
                x = scanner.nextInt();
                System.out.println("Enter y");
                y = scanner.nextInt();
                System.out.println("Enter z");
                z = scanner.nextInt();
            } catch (InputMismatchException exception) {
                System.err.println("Your values are not integers. Try again");
                scanner.next();
                continue;
            } catch (NoSuchElementException exception) {
                System.err.println("Not enough data");
                return;
            }
            String result;
            try {
                result = String.valueOf(expression.evaluate(x, y, z));
            } catch (ArithmeticException exception) {
                result = exception.getMessage();
            }
            System.out.printf("%10s %10s %10s %10s\n", x, y, z, result);
        }
    }

}