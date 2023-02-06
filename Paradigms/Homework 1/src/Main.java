import expression.Expression;
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
            expressionString = scanner.next();
        } catch (NoSuchElementException exception) {
            System.err.println("No expression was entered");
            return;
        }
        ExpressionParser parser = new ExpressionParser();
        Expression expression;
        try {
            expression = parser.parse(expressionString);
        } catch (ParseExpressionException e) {
            System.err.println(e.getMessage());
            return;
        }
        System.out.printf("%10s %10s\n", "x", "f");
        while (scanner.hasNext()) {
            int value;
            try {
                value = scanner.nextInt();
            } catch (InputMismatchException exception) {
                System.err.println("Input value is not integer");
                scanner.next();
                continue;
            }
            String result;
            try {
                result = String.valueOf(expression.evaluate(value));
            } catch (ArithmeticException exception) {
                result = exception.getMessage();
            }
            System.out.printf("%10s %10s\n", value, result);
        }
    }

}