package expression.exceptions.exceptions;

public class ParseExpressionException extends Exception {
    public ParseExpressionException() {
    }

    public ParseExpressionException(String message) {
        super(message);
    }

    public ParseExpressionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParseExpressionException(Throwable cause) {
        super(cause);
    }

    public ParseExpressionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
