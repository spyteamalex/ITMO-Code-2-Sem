package queue;
public class ArrayQueueADTTests {
    public static void fill(ArrayQueueADT queue, String name) {
        System.out.println("queue: " + name + " size: " + ArrayQueueADT.size(queue));
        for (int i = 0; i < 10; i++) {
            ArrayQueueADT.enqueue(queue, name + "_" + i);
            System.out.println("queue: " + name + " size: " + ArrayQueueADT.size(queue) + " enqueue: " + name + "_" + i);
        }
    }

    public static void dump(ArrayQueueADT queue, String name) {
        while (!ArrayQueueADT.isEmpty(queue)) {
            System.out.println("queue: " + name + " size: " + ArrayQueueADT.size(queue) + " element: " + ArrayQueueADT.element(queue));
            System.out.println("queue: " + name + " size: " + ArrayQueueADT.size(queue) + " dequeue: " + ArrayQueueADT.dequeue(queue));
        }
        System.out.println("queue: " + name + " size: " + ArrayQueueADT.size(queue));
    }

    public static void main(String[] args) {
        ArrayQueueADT queue1 = ArrayQueueADT.create();
        ArrayQueueADT queue2 = ArrayQueueADT.create();
        final String queue1Name = "q1";
        final String queue2Name = "q2";
        fill(queue1, queue1Name);
        fill(queue2, queue2Name);
        dump(queue1, queue1Name);
        dump(queue2, queue2Name);
    }
}