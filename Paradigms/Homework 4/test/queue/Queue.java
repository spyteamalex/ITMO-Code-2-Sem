package queue;

public interface Queue extends Iterable<Object> {
    /**
     * a' -- новое значение, a -- старое
     * Будем считать, что для i >= a.length  a[i] := a[i % a.length]
     * Будем считать, что для i < 0  a[i] := a[((i % a.length) + a.length) % a.length]
     * Let: immutable(l, l', len) = \forall 0 <= i < len  data[l + i] == data'[l' + i]
     * Let: const(a) = (a' == a)
     * Inv: 0 <= size <= data.length && 0 <= head < data.length && \forall head <= i < head + size  data[i] != null
     */


    /**
     * Pre: el != null
     * Post: size' == size + 1 && immutable(head, head', size) && data'[head' + size] == el
     */
    void enqueue(Object el);

    /**
     * Pre: size > 0
     * Post: const(size) && immutable(head, head', size) && R == data[head]
     */
    Object element();

    /**
     * Pre: size > 0
     * Post: size' == size - 1 && immutable(head, head', size') && R == data[head]
     */
    Object dequeue();

    /**
     * Pre: true
     * Post: const(size) && immutable(head, head', size) && R == size
     */
    int size();

    /**
     * Pre: true
     * Post: const(size) && immutable(head, head', size) && R == (size == 0)
     */
    boolean isEmpty();

    /**
     * Pre: true
     * Post: size == 0
     */
    void clear();


    /**
     * Pre: true
     * Post: const(size) && immutable(head, head', size) && R.size == size / n && \forall 0 <= i < R.size  R.data[R.head + i] == data[head + ni]
     */
    Queue getNth(int n);


    /**
     * Pre: true
     * Post: size' = size - size/n && \forall 0 <= i < size, i % n != 0  data'[head' + i - i/n] == data[head + i] && R.size == size / n && \forall 0 <= i < R.size  R.data[R.head + i] == data[head + ni]
     */
    Queue removeNth(int n);


    /**
     * Pre: true
     * Post: size' = size - size/n && \forall 0 <= i < size, i % n != 0  data'[head' + i - i/n] == data[head + i]
     */
    void dropNth(int n);
}
