package expression.exceptions;

import expression.tools.CharStack;

public class StringReader {
    private static final char END = '\0';
    private final CharSource source;
    private final CharStack buffer = new CharStack();

    private int position = 0;

    public StringReader(final CharSource source) {
        this.source = source;
    }

    public char take() {
        if (!buffer.isEmpty()) {
            position++;
            return buffer.pop();
        }
        return source.hasNext() ? source.next() : END;
    }

    public char test() {
        if (buffer.isEmpty()) {
            if (source.hasNext()) {
                buffer.add(source.next());
            } else {
                return END;
            }
        }
        return buffer.top();
    }

    public boolean take(String str) {
        if (test(str)) {
            position += str.length();
            buffer.pop(str.length());
            return true;
        }
        return false;
    }

    public boolean test(final char expected) {
        if (buffer.isEmpty()) {
            if (!source.hasNext()) {
                return expected == END;
            }
            buffer.add(source.next());
        }
        return buffer.top() == expected;
    }

    public boolean test(final String expected) {
        int i = 0;
        boolean result = true;
        for (; i < expected.length(); i++) {
            if (!take(expected.charAt(i))) {
                result = false;
                break;
            }
        }
        position -= i;
        for (i -= 1; i >= 0; i--) {
            buffer.add(expected.charAt(i));
        }
        return result;
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
