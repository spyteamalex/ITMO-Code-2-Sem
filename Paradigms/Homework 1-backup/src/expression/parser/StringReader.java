package expression.parser;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class StringReader {
    private static final char END = '\0';
    private final CharSource source;
    private char ch = 0xffff;

    protected StringReader(final CharSource source) {
        this.source = source;
        take();
    }

    protected char take() {
        final char result = ch;
        ch = source.hasNext() ? source.next() : END;
        return result;
    }

    protected boolean take(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!take(str.charAt(i))) {
                for (; i > 0; i--) {
                    ch = source.prev();
                }
                return false;
            }
        }
        return true;
    }

    protected boolean test(final char expected) {
        return ch == expected;
    }

    protected boolean test(final String expected) {
        if (take(expected)) {
            for (int i = 0; i < expected.length(); i++) {
                ch = source.prev();
            }
            return true;
        }
        return false;
    }

    protected char get() {
        return ch;
    }

    protected boolean take(final char expected) {
        if (test(expected)) {
            take();
            return true;
        }
        return false;
    }

    protected void expect(final char expected) {
        if (!take(expected)) {
            Tools.raiseError("Expected '" + expected + "', found '" + ch + "'");
        }
    }

    protected void expect(final String value) {
        for (final char c : value.toCharArray()) {
            expect(c);
        }
    }

    protected boolean eof() {
        return take(END);
    }

    protected boolean between(final char from, final char to) {
        return from <= ch && ch <= to;
    }
}
