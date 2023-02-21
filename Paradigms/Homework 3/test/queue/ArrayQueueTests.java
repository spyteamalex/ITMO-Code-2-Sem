package queue;
public class ArrayQueueTests {
    public static void fill(ArrayQueue queue, String name) {
        System.out.println("queue: " + name + " size: " + queue.size());
        for (int i = 0; i < 10; i++) {
            queue.enqueue(name + "_" + i);
            System.out.println("queue: " + name + " size: " + queue.size() + " enqueue: " + name + "_" + i);
        }
    }

    public static void dump(ArrayQueue queue, String name) {
        while (!queue.isEmpty()) {
            System.out.println("queue: " + name + " size: " + queue.size() + " element: " + queue.element());
            System.out.println("queue: " + name + " size: " + queue.size() + " dequeue: " + queue.dequeue());
        }
        System.out.println("queue: " + name + " size: " + queue.size());
    }

    public static void main(String[] args) {
        ArrayQueue queue1 = new ArrayQueue();
        ArrayQueue queue2 = new ArrayQueue();
        final String queue1Name = "q1";
        final String queue2Name = "q2";
        fill(queue1, queue1Name);
        fill(queue2, queue2Name);
        dump(queue1, queue1Name);
        dump(queue2, queue2Name);
    }
}