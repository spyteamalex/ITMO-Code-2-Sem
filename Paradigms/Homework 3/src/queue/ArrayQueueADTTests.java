package queue;
public class ArrayQueueADTTests {
    public static void fill_head(ArrayQueueADT queue, String name) {
        System.out.println("queue: " + name + " size: " + ArrayQueueADT.size(queue));
        for (int i = 0; i < 10; i++) {
            String el = name + "_head_" + i;
            ArrayQueueADT.enqueue(queue, el);
            System.out.println("queue: " + name + " size: " + ArrayQueueADT.size(queue) + " enqueue: " + el);
        }
    }

    public static void fill_tail(ArrayQueueADT queue, String name) {
        System.out.println("queue: " + name + " size: " + ArrayQueueADT.size(queue));
        for (int i = 0; i < 10; i++) {
            String el = name + "_tail_" + i;
            ArrayQueueADT.push(queue, el);
            System.out.println("queue: " + name + " size: " + ArrayQueueADT.size(queue) + " enqueue: " + el);
        }
    }

    public static void set(ArrayQueueADT queue, String name) {
        System.out.println("queue: " + name + " size: " + ArrayQueueADT.size(queue));
        for (int i = 0; i < 10; i++) {
            String el = name + "_set_" + i;
            ArrayQueueADT.set(queue, i, el);
            System.out.println("queue: " + name + " size: " + ArrayQueueADT.size(queue) + " enqueue: " + el);
        }
    }

    public static void get(ArrayQueueADT queue, String name) {
        System.out.println("queue: " + name + " size: " + ArrayQueueADT.size(queue));
        for (int i = 0; i < 10; i++) {
            System.out.println("queue: " + name + " size: " + ArrayQueueADT.size(queue) + " get: " + ArrayQueueADT.get(queue, i));
        }
    }

    public static void dump_head(ArrayQueueADT queue, String name) {
        while (!ArrayQueueADT.isEmpty(queue)) {
            System.out.println("queue: " + name + " size: " + ArrayQueueADT.size(queue) + " element: " + ArrayQueueADT.element(queue));
            System.out.println("queue: " + name + " size: " + ArrayQueueADT.size(queue) + " dequeue: " + ArrayQueueADT.dequeue(queue));
        }
        System.out.println("queue: " + name + " size: " + ArrayQueueADT.size(queue));
    }

    public static void dump_tail(ArrayQueueADT queue, String name) {
        while (!ArrayQueueADT.isEmpty(queue)) {
            System.out.println("queue: " + name + " size: " + ArrayQueueADT.size(queue) + " element: " + ArrayQueueADT.peek(queue));
            System.out.println("queue: " + name + " size: " + ArrayQueueADT.size(queue) + " dequeue: " + ArrayQueueADT.remove(queue));
        }
        System.out.println("queue: " + name + " size: " + ArrayQueueADT.size(queue));
    }

    public static void main(String[] args) {
        ArrayQueueADT queue1 = ArrayQueueADT.create();
        final String queue1Name = "q1";
        fill_head(queue1, queue1Name);
        dump_head(queue1, queue1Name);
        fill_tail(queue1, queue1Name);
        set(queue1, queue1Name);
        get(queue1, queue1Name);
        dump_tail(queue1, queue1Name);
    }
}