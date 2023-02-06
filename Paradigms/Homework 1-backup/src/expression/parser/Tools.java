package expression.parser;

public class Tools {

    protected static void raiseError(final String message) {
        throw new RuntimeException(message);
    }

    protected static void require(boolean condition, final String message) {
        if (!condition) {
            raiseError(message);
        }
    }
}
