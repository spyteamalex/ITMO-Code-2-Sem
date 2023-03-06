package queue;

import java.util.Iterator;
import java.util.Objects;

public abstract class AbstractQueue implements Queue, Iterable<Object> {
    /**
     * a' -- новое значение, a -- старое
     * Будем считать, что для i >= a.length  a[i] := a[i % a.length]
     * Будем считать, что для i < 0  a[i] := a[((i % a.length) + a.length) % a.length]
     * Let: immutable(l, l', len) = \forall 0 <= i < len  data[l + i] == data'[l' + i]
     * Let: const(a) = (a' == a)
     * Inv: 0 <= size <= data.length && 0 <= head < data.length && \forall head <= i < head + size  data[i] != null
     */
    protected int size;

    public AbstractQueue() {
        size = 0;
    }

    /**
     * Pre: el != null
     * Post: size' == size + 1 && immutable(head, head', size) && data'[head' + size] == el
     */
    @Override
    public void enqueue(Object el) {
        Objects.requireNonNull(el);
        enqueueImp(el);
        size++;
    }

    protected abstract void enqueueImp(Object el);


    /**
     * Pre: size > 0
     * Post: const(size) && immutable(head, head', size) && R == data[head]
     */
    @Override
    public Object element() {
        assert !isEmpty();
        return elementImp();
    }

    protected abstract Object elementImp();


    /**
     * Pre: size > 0
     * Post: size' == size - 1 && immutable(head, head', size') && R == data[head]
     */
    @Override
    public Object dequeue() {
        assert !isEmpty();
        Object res = element();
        dequeueImpl();
        size--;
        return res;
    }

    protected abstract void dequeueImpl();

    /**
     * Pre: true
     * Post: const(size) && immutable(head, head', size) && R == size
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Pre: true
     * Post: const(size) && immutable(head, head', size) && R == (size == 0)
     */
    @Override
    public boolean isEmpty(){
        return size == 0;
    }


    /**
     * Pre: true
     * Post: size == 0
     */
    @Override
    public void clear() {
        clearImpl();
        size = 0;
    }

    protected abstract void clearImpl();


    /**
     * Pre: true
     * Post: const(size) && immutable(head, head', size) && R.size == size / n && \forall 0 <= i < R.size  R.data[R.head + i] == data[head + ni]
     */
    public Queue getNth(int n) {
        int i = 0;
        Queue res = create();
        for (Iterator<Object> it = iterator(); it.hasNext(); i++) {
            Object value = it.next();
            if ((i + 1) % n == 0) {
                res.enqueue(value);
            }
        }
        return res;
    }

    protected abstract Queue create();


    /**
     * Pre: true
     * Post: size' = size - size/n && \forall 0 <= i < size, i % n != 0  data'[head' + i - i/n] == data[head + i] && R.size == size / n && \forall 0 <= i < R.size  R.data[R.head + i] == data[head + ni]
     */
    public Queue removeNth(int n) {
        Queue res = getNth(n);
        dropNth(n);
        return res;
    }


    /**
     * Pre: true
     * Post: size' = size - size/n && \forall 0 <= i < size, i % n != 0  data'[head' + i - i/n] == data[head + i]
     */
    public void dropNth(int n) {
        int i = 0;
        Queue res = create();
        for (Iterator<Object> it = iterator(); it.hasNext(); i++) {
            Object value = it.next();
            if ((i + 1) % n != 0) {
                res.enqueue(value);
            }
        }
        clear();
        for (Object o : res) {
            enqueue(o);
        }
    }
}
