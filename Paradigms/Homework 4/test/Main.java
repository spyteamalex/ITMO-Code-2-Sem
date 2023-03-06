import queue.ArrayQueue;
import queue.LinkedQueue;
import queue.Queue;

public class Main {
    public static void main(String[] args) {
        Queue lq = new ArrayQueue();
        lq.enqueue(2);
        lq.getNth(2);
        lq.removeNth(2);
        lq.enqueue(2);
        System.out.println(lq);
    }
}
