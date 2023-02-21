package queue;

import java.util.Objects;

public class ArrayQueueModule {
    /**
     * a' -- новое значение, a -- старое
     * Будем считать, что для i >= a.length  a[i] := a[i % a.length]
     * Будем считать, что для i < 0  a[i] := a[((i % a.length) + a.length) % a.length]
     * Let: immutable(l, l', len) = \forall 0 <= i < len  data[l + i] == data'[l' + i]
     * Let: const(a) = (a' == a)
     * Inv: 0 <= size <= data.length && 0 <= head < data.length && \forall head <= i < head + size  data[i] != null
     */
    private static final int DEFAULT_SIZE = 10;
    private static int size = 0;
    private static int head = 0;
    private static Object[] data = new Object[DEFAULT_SIZE];

    private static int inBounds(int index, int bounds) {
        return ((index % bounds) + bounds) % bounds;
    }

    /**
     * Pre: el != null
     * Post: size' == size + 1 && immutable(head, head', size) && data'[head' + size] == el
     */
    public static void enqueue(Object el) {
        Objects.requireNonNull(el);
        ensureCapacity(size + 1);
        data[inBounds(head + size++, data.length)] = el;
    }


    /**
     * Pre: true
     * Post: size' == size && immutable(head, head', size) && capacity <= data'.length
     */
    private static void ensureCapacity(int capacity) {
        if (capacity > data.length) {
            int newCapacity = Math.max(capacity, 2 * data.length);
            Object[] newData = new Object[newCapacity];
            System.arraycopy(data, head, newData, 0, Math.min(size, data.length - head));
            System.arraycopy(data, 0, newData, data.length - head, Math.max(head + size - data.length, 0));
            data = newData;
            head = 0;
        }
    }


    /**
     * Pre: size > 0
     * Post: const(size) && immutable(head, head', size) && R == data[head]
     */
    public static Object element() {
        return get(0);
    }


    /**
     * Pre: size > 0
     * Post: size' == size - 1 && immutable(head, head', size') && R == data[head]
     */
    public static Object dequeue() {
        assert !isEmpty();
        Object res = data[head];
        size--;
        data[head] = null;
        head = inBounds(head + 1, data.length);
        return res;
    }

    /**
     * Pre: true
     * Post: const(size) && immutable(head, head', size) && R == size
     */
    public static int size() {
        return size;
    }

    /**
     * Pre: true
     * Post: const(size) && immutable(head, head', size) && R == (size == 0)
     */
    public static boolean isEmpty(){
        return size == 0;
    }


    /**
     * Pre: true
     * Post: size == 0
     */
    public static void clear() {
        size = 0;
    }


    /**
     * Pre: 0 <= index < size
     * Post: const(size) && immutable(head, head', size) && R == data'[head' + index]
     */
    public static Object get(int index) {
        assert 0 <= index && index < size;
        return data[inBounds(head + index, data.length)];
    }


    /**
     * Pre: 0 <= index < size && el != null
     * Post: const(size) && immutable(head, head', index) && data'[head' + index] == el && immutable(head + index + 1, head' + index + 1, size - index - 1)
     */
    public static void set(int index, Object el) {
        Objects.requireNonNull(el);
        assert 0 <= index && index < size;
        data[inBounds(head + index, data.length)] = el;
    }


    /**
     * Pre: el != null
     * Post: size' == size + 1 && immutable(head, head' + 1, size) && data'[head'] == el
     */
    public static void push(Object el) {
        Objects.requireNonNull(el);
        ensureCapacity(++size);
        head = inBounds(head - 1, data.length);
        data[head] = el;
    }


    /**
     * Pre: size > 0
     * Post: const(size) && immutable(head, head', size) && R == data'[head' + size' - 1]
     */
    public static Object peek() {
        return get(size - 1);
    }


    /**
     * Pre: size > 0
     * Post: size' == size - 1 && immutable(head, head', size') && R == data[head + size']
     */
    public static Object remove() {
        assert !isEmpty();
        int index = inBounds(head + --size, data.length);
        Object res = data[index];
        data[index] = null;
        return res;
    }
}
