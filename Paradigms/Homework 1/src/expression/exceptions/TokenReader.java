package expression.exceptions;

public class TokenReader {

    public static final String END = String.valueOf(StringReader.END);
    private final StringReader reader;
    private String token;

    public TokenReader(StringSource source) {
        this(new StringReader(source));
    }

    public TokenReader(StringReader reader) {
        this.reader = reader;
        take();
    }

    private String readToken() {
        skipSpaces();
        char first = reader.take();
        if (!Character.isLetterOrDigit(first)) {
            return String.valueOf(first);
        } else {
            StringBuilder newToken = new StringBuilder(String.valueOf(first));
            while (Character.isLetterOrDigit(reader.get())) {
                newToken.append(reader.take());
            }
            return newToken.toString();
        }
    }

    public String take() {
        String oldToken = token;
        token = readToken();
        return oldToken;
    }

    public boolean test(String expected) {
        return token.equals(expected);
    }

    public String get() {
        return token;
    }

    public boolean take(String expected) {
        if (test(expected)) {
            take();
            return true;
        }
        return false;
    }

    public boolean eof() {
        return test(END);
    }

    public int getPosition() {
        return reader.getPosition() - token.length();
    }

    private void skipSpaces() {
        while (Character.isWhitespace(reader.get())) {
            reader.take();
        }
    }
}
