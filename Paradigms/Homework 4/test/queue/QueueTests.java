package queue;
public class QueueTests {
    public static void fill(Queue queue, String name) {
        System.out.println("queue: " + name + " size: " + queue.size());
        for (int i = 0; i < 10; i++) {
            String el = name + "_head_" + i;
            queue.enqueue(el);
            System.out.println("queue: " + name + " size: " + queue.size() + " enqueue: " + el);
        }
    }
    public static void dump(Queue queue, String name) {
        while (!queue.isEmpty()) {
            System.out.println("queue: " + name + " size: " + queue.size() + " element: " + queue.element());
            System.out.println("queue: " + name + " size: " + queue.size() + " dequeue: " + queue.dequeue());
        }
        System.out.println("queue: " + name + " size: " + queue.size());
    }

    public static void main(String[] args) {
        Queue arrayQueue = new ArrayQueue();
        Queue linkedQueue = new LinkedQueue();
        final String arrayQueueName = "aq";
        final String linkedQueueName = "lk";
        fill(arrayQueue, arrayQueueName);
        dump(arrayQueue, arrayQueueName);
        fill(linkedQueue, linkedQueueName);
        dump(linkedQueue, linkedQueueName);

    }
}