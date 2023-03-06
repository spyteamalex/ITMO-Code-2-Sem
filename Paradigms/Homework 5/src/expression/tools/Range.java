package expression.tools;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Range implements Iterable<Integer> {
    private final int start, end;

    private Range(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public static Range of(int start, int end) {
        return new Range(start, end);
    }

    @Override
    public Iterator<Integer> iterator() {
        return new RangeIterator(start, end);
    }

    public static final class RangeIterator implements Iterator<Integer> {
        private final int end;
        private int pointer;
        private final int step;

        public RangeIterator(int start, int end) {
            this.end = end;
            this.pointer = start;
            this.step = start <= end ? 1 : -1;
        }

        @Override
        public boolean hasNext() {
            return pointer == end || ((pointer < end) == (step == 1));
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Unexpected end of data");
            }
            int res = pointer;
            pointer += step;
            return res;
        }
    }
}
