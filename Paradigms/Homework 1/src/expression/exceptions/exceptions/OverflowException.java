package expression.exceptions.exceptions;

public class OverflowException extends ArithmeticException{
    public OverflowException() {
    }

    public OverflowException(String s) {
        super(s);
    }
}
