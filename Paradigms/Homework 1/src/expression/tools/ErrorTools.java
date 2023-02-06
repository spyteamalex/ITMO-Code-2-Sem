package expression.tools;

public class ErrorTools {

    public static void raiseError(final String message) {
        throw new RuntimeException(message);
    }

    public static <T extends Exception> void require(boolean condition, final T exception) throws T {
        if (!condition) {
            throw exception;
        }
    }
}
