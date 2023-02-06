package expression.exceptions;
public class StringSource implements CharSource {
    private final String data;
    private int pos = -1;

    public StringSource(final String data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return pos < data.length() - 1;
    }

    @Override
    public char next() {
        return data.charAt(++pos);
    }
}
