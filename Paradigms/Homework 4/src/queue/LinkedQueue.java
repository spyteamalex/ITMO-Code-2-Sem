package queue;

import java.util.Iterator;

public class LinkedQueue extends AbstractQueue {

    private final class QueueIterator implements Iterator<Object> {

        private Node next = head;
        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public Object next() {
            Object res = next.value;
            next = next.next;
            return res;
        }
    }

    private static final class Node {
        private Object value;
        private Node prev;
        private Node next;

        private Node(Object value, Node prev, Node next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node head;

    private Node tail;

    public LinkedQueue() {
        head = null;
        tail = null;
    }


    @Override
    protected void enqueueImp(Object el) {
        Node newNode = new Node(el, tail, null);
        if (isEmpty()) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
    }

    @Override
    protected Object elementImp() {
        return head.value;
    }

    @Override
    protected void dequeueImpl() {
        head = head.next;
        if (head == null) {
            tail = null;
        }
    }

    @Override
    protected void clearImpl() {
        head = null;
        tail = null;
    }

    @Override
    protected Queue create() {
        return new LinkedQueue();
    }

    @Override
    public Iterator<Object> iterator() {
        return new QueueIterator();
    }
}
