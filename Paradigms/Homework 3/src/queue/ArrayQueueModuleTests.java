package queue;

public class ArrayQueueModuleTests {
    public static void fill_head() {
        System.out.println("size: " + ArrayQueueModule.size());
        for (int i = 0; i < 10; i++) {
            String el = "head_" + i;
            ArrayQueueModule.enqueue(el);
            System.out.println("size: " + ArrayQueueModule.size() + " enqueue: " + el);
        }
    }

    public static void fill_tail() {
        System.out.println("size: " + ArrayQueueModule.size());
        for (int i = 0; i < 10; i++) {
            String el = "tail_" + i;
            ArrayQueueModule.push(el);
            System.out.println("size: " + ArrayQueueModule.size() + " enqueue: " + el);
        }
    }

    public static void set() {
        System.out.println("size: " + ArrayQueueModule.size());
        for (int i = 0; i < 10; i++) {
            String el = "set_" + i;
            ArrayQueueModule.set(i, el);
            System.out.println("size: " + ArrayQueueModule.size() + " enqueue: " + el);
        }
    }

    public static void get() {
        System.out.println("size: " + ArrayQueueModule.size());
        for (int i = 0; i < 10; i++) {
            System.out.println("size: " + ArrayQueueModule.size() + " get: " + ArrayQueueModule.get(i));
        }
    }

    public static void dump_head() {
        while (!ArrayQueueModule.isEmpty()) {
            System.out.println("size: " + ArrayQueueModule.size() + " element: " + ArrayQueueModule.element());
            System.out.println("size: " + ArrayQueueModule.size() + " dequeue: " + ArrayQueueModule.dequeue());
        }
        System.out.println("size: " + ArrayQueueModule.size());
    }

    public static void dump_tail() {
        while (!ArrayQueueModule.isEmpty()) {
            System.out.println("size: " + ArrayQueueModule.size() + " element: " + ArrayQueueModule.peek());
            System.out.println("size: " + ArrayQueueModule.size() + " dequeue: " + ArrayQueueModule.remove());
        }
        System.out.println("size: " + ArrayQueueModule.size());
    }

    public static void main(String[] args) {
        fill_head();
        dump_head();
        fill_tail();
        set();
        get();
        dump_tail();
    }
}