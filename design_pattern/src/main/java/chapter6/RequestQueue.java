package chapter6;

import java.util.LinkedList;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-08-04 22:00
 **/
public class RequestQueue {
    private final LinkedList<Request> queue = new LinkedList<>();

    public Request getRequest() {
        synchronized (queue) {
            while (queue.size() <= 0) {
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    return null;
                }
            }
            return queue.removeFirst();
        }
    }

    public void putRequest(Request request) {
        synchronized (queue) {
            queue.addLast(request);
            queue.notifyAll();
        }
    }
}
