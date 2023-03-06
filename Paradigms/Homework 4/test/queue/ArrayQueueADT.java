package queue;

import java.util.Objects;

public class ArrayQueueADT {
    /**
     * a' -- новое значение, a -- старое
     * Будем считать, что для i >= a.length  a[i] := a[i % a.length]
     * Будем считать, что для i < 0  a[i] := a[((i % a.length) + a.length) % a.length]
     * Let: immutable(l, l', len) = \forall 0 <= i < len  data[l + i] == data'[l' + i]
     * Let: const(a) = (a' == a)
     * Inv: 0 <= size <= data.length && 0 <= head < data.length && \forall head <= i < head + size  data[i] != null
     */
    private static final int DEFAULT_SIZE = 10;
    private int size = 0;
    private int head = 0;
    private Object[] data = new Object[DEFAULT_SIZE];

    public static ArrayQueueADT create() {
        return new ArrayQueueADT();
    }

    private static int inBounds(ArrayQueueADT ths, int index) {
        int bound = ths.data.length;
        return ((ths.head + index % bound) + bound) % bound;
    }

    /**
     * Pre: el != null && ths != null
     * Post: size' == size + 1 && immutable(head, head', size) && data'[head' + size] == el
     */
    public static void enqueue(ArrayQueueADT ths, Object el) {
        Objects.requireNonNull(ths);
        Objects.requireNonNull(el);
        ensureCapacity(ths,ths.size + 1);
        ths.data[inBounds(ths, ths.size++)] = el;
    }


    /**
     * Pre: ths != null
     * Post: size' == size && immutable(head, head', size) && capacity <= data'.length
     */
    private static void ensureCapacity(ArrayQueueADT ths, int capacity) {
        Objects.requireNonNull(ths);
        if (capacity > ths.data.length) {
            int newCapacity = Math.max(capacity, 2 * ths.data.length);
            Object[] newData = new Object[newCapacity];
            System.arraycopy(ths.data, ths.head, newData, 0, Math.min(ths.size, ths.data.length - ths.head));
            System.arraycopy(ths.data, 0, newData, ths.data.length - ths.head, Math.max(ths.head + ths.size - ths.data.length, 0));
            ths.data = newData;
            ths.head = 0;
        }
    }


    /**
     * Pre: size > 0 && ths != null
     * Post: const(size) && immutable(head, head', size) && R == data[head]
     */
    public static Object element(ArrayQueueADT ths) {
        assert !isEmpty(ths);
        return get(ths, 0);
    }


    /**
     * Pre: size > 0 && ths != null
     * Post: size' == size - 1 && immutable(head, head', size') && R == data[head]
     */
    public static Object dequeue(ArrayQueueADT ths) {
        Objects.requireNonNull(ths);
        assert !isEmpty(ths);
        Object res = ths.data[ths.head];
        ths.size--;
        ths.data[ths.head] = null;
        ths.head = inBounds(ths, 1);
        return res;
    }

    /**
     * Pre: ths != null
     * Post: const(size) && immutable(head, head', size) && R == size
     */
    public static int size(ArrayQueueADT ths) {
        Objects.requireNonNull(ths);
        return ths.size;
    }

    /**
     * Pre: ths != null
     * Post: const(size) && immutable(head, head', size) && R == (size == 0)
     */
    public static boolean isEmpty(ArrayQueueADT ths){
        Objects.requireNonNull(ths);
        return ths.size == 0;
    }


    /**
     * Pre: ths != null
     * Post: size == 0
     */
    public static void clear(ArrayQueueADT ths) {
        Objects.requireNonNull(ths);
        ths.size = 0;
    }


    /**
     * Pre: 0 <= index < size
     * Post: const(size) && immutable(head, head', size) && R == data'[head' + index]
     */
    public static Object get(ArrayQueueADT ths, int index) {
        assert 0 <= index && index < ths.size;
        return ths.data[inBounds(ths, index)];
    }


    /**
     * Pre: 0 <= index < size && el != null
     * Post: const(size) && immutable(head, head', index) && data'[head' + index] == el && immutable(head + index + 1, head' + index + 1, size - index - 1)
     */
    public static void set(ArrayQueueADT ths, int index, Object el) {
        Objects.requireNonNull(el);
        assert 0 <= index && index < ths.size;
        ths.data[inBounds(ths, index)] = el;
    }


    /**
     * Pre: el != null
     * Post: size' == size + 1 && immutable(head, head' + 1, size) && data'[head'] == el
     */
    public static void push(ArrayQueueADT ths, Object el) {
        Objects.requireNonNull(el);
        ensureCapacity(ths, ++ths.size);
        ths.head = inBounds(ths, - 1);
        ths.data[ths.head] = el;
    }


    /**
     * Pre: size > 0
     * Post: const(size) && immutable(head, head', size) && R == data'[head' + size' - 1]
     */
    public static Object peek(ArrayQueueADT ths) {
        return get(ths, ths.size - 1);
    }


    /**
     * Pre: size > 0
     * Post: size' == size - 1 && immutable(head, head', size') && R == data[head + size']
     */
    public static Object remove(ArrayQueueADT ths) {
        assert !isEmpty(ths);
        int index = inBounds(ths, --ths.size);
        Object res = ths.data[index];
        ths.data[index] = null;
        return res;
    }
}
