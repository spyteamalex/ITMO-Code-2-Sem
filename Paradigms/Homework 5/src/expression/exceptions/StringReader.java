package expression.exceptions;

public class StringReader {
    public static final char END = '\0';
    private final CharSource source;
    private char ch = 0xffff;
    private int position = -1;

    public StringReader(final CharSource source) {
        this.source = source;
        take();
    }

    public char take() {
        final char result = ch;
        ch = source.hasNext() ? source.next() : END;
        position++;
        return result;
    }

    public boolean test(final char expected) {
        return ch == expected;
    }

    public char get() {
        return ch;
    }

    public boolean take(final char expected) {
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
        return position;
    }
}
