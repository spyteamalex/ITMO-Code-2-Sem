package queue;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayQueue extends AbstractQueue {
    private final class QueueIterator implements Iterator<Object> {

        private int i = 0;
        @Override
        public boolean hasNext() {
            return i < size;
        }

        @Override
        public Object next() {
            return data[shift(i++)];
        }
    }

    private static final int DEFAULT_SIZE = 10;
    private int head;
    private Object[] data;

    public ArrayQueue() {
        head = 0;
        data = new Object[DEFAULT_SIZE];
    }

    private int shift(int index) {
        return ((head + index % data.length) + data.length) % data.length;
    }


    private void ensureCapacity(int capacity) {
        if (capacity > data.length) {
            int newCapacity = Math.max(capacity, 2 * data.length);
            Object[] newData = new Object[newCapacity];
            System.arraycopy(data, head, newData, 0, Math.min(size, data.length - head));
            System.arraycopy(data, 0, newData, data.length - head, Math.max(head + size - data.length, 0));
            data = newData;
            head = 0;
        }
    }


    @Override
    protected void enqueueImp(Object el) {
        ensureCapacity(size + 1);
        data[shift(size)] = el;
    }

    @Override
    protected Object elementImp() {
        return data[head];
    }

    @Override
    protected void dequeueImpl() {
        data[head] = null;
        head = shift(1);
    }

    @Override
    protected void clearImpl() {
        head = 0;
        Arrays.fill(data, null);
    }

    @Override
    protected Queue create() {
        return new ArrayQueue();
    }

    @Override
    public Iterator<Object> iterator() {
        return new QueueIterator();
    }
}
