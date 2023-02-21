package queue;
public class ArrayQueueTests {
    public static void fill_head(ArrayQueue queue, String name) {
        System.out.println("queue: " + name + " size: " + queue.size());
        for (int i = 0; i < 10; i++) {
            String el = name + "_head_" + i;
            queue.enqueue(el);
            System.out.println("queue: " + name + " size: " + queue.size() + " enqueue: " + el);
        }
    }

    public static void fill_tail(ArrayQueue queue, String name) {
        System.out.println("queue: " + name + " size: " + queue.size());
        for (int i = 0; i < 10; i++) {
            String el = name + "_tail_" + i;
            queue.push(el);
            System.out.println("queue: " + name + " size: " + queue.size() + " enqueue: " + el);
        }
    }

    public static void set(ArrayQueue queue, String name) {
        System.out.println("queue: " + name + " size: " + queue.size());
        for (int i = 0; i < 10; i++) {
            String el = name + "_set_" + i;
            queue.set(i, el);
            System.out.println("queue: " + name + " size: " + queue.size() + " enqueue: " + el);
        }
    }

    public static void get(ArrayQueue queue, String name) {
        System.out.println("queue: " + name + " size: " + queue.size());
        for (int i = 0; i < 10; i++) {
            System.out.println("queue: " + name + " size: " + queue.size() + " get: " + queue.get(i));
        }
    }

    public static void dump_head(ArrayQueue queue, String name) {
        while (!queue.isEmpty()) {
            System.out.println("queue: " + name + " size: " + queue.size() + " element: " + queue.element());
            System.out.println("queue: " + name + " size: " + queue.size() + " dequeue: " + queue.dequeue());
        }
        System.out.println("queue: " + name + " size: " + queue.size());
    }

    public static void dump_tail(ArrayQueue queue, String name) {
        while (!queue.isEmpty()) {
            System.out.println("queue: " + name + " size: " + queue.size() + " element: " + queue.peek());
            System.out.println("queue: " + name + " size: " + queue.size() + " dequeue: " + queue.remove());
        }
        System.out.println("queue: " + name + " size: " + queue.size());
    }

    public static void main(String[] args) {
        ArrayQueue queue1 = new ArrayQueue();
        final String queue1Name = "q1";
        fill_head(queue1, queue1Name);
        dump_head(queue1, queue1Name);
        fill_tail(queue1, queue1Name);
        set(queue1, queue1Name);
        get(queue1, queue1Name);
        dump_tail(queue1, queue1Name);
    }
}