package queue;

public class ArrayQueueModuleTests {
    public static void fill() {
        System.out.println("size: " + ArrayQueueModule.size());
        for (int i = 0; i < 10; i++) {
            ArrayQueueModule.enqueue("el_" + i);
            System.out.println("size: " + ArrayQueueModule.size() + " enqueue: el_" + i);
        }
    }

    public static void dump() {
        while (!ArrayQueueModule.isEmpty()) {
            System.out.println("size: " + ArrayQueueModule.size() + " element: " + ArrayQueueModule.element());
            System.out.println("size: " + ArrayQueueModule.size() + " dequeue: " + ArrayQueueModule.dequeue());
        }
        System.out.println("size: " + ArrayQueueModule.size());
    }

    public static void main(String[] args) {
        fill();
        dump();
    }
}