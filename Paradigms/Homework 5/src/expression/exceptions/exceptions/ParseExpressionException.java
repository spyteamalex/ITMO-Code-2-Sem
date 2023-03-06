package expression.exceptions.exceptions;

public class ParseExpressionException extends Exception {

    protected final int position;

    public ParseExpressionException(int position) {
        this.position = position;
    }

    public ParseExpressionException(String message, int position) {
        super(message);
        this.position = position;
    }

    public ParseExpressionException(String message, int position, Throwable cause) {
        super(message, cause);
        this.position = position;
    }

    public ParseExpressionException(int position, Throwable cause) {
        super(cause);
        this.position = position;
    }

    public ParseExpressionException(String message, int position, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.position = position;
    }
}
