package chapter16;

import java.util.Arrays;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-08-26 20:47
 **/
public class Channel {
    private final static int MAX_REQUESTS = 100;
    private final Request[] requests;
    private final WorkerThread[] workerPool;
    private int head;
    private int tail;
    private int count;

    public Channel(int workers) {

        this.workerPool = new WorkerThread[workers];
        this.requests = new Request[MAX_REQUESTS];
        this.head = 0;
        this.tail = 0;
        this.count = 0;
        this.init();

    }

    private void init() {
        for (int i = 0; i < workerPool.length; i++) {
            workerPool[i] = new WorkerThread("Worker" + i, this);
        }
    }
    public synchronized void startWork(){
        Arrays.stream(workerPool).forEach(WorkerThread::start);
    }

    public synchronized void put(Request request) {
        while (count > requests.length) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.requests[tail] = request;
        this.tail = (tail + 1) % requests.length;
        this.count++;
        this.notifyAll();
    }

    public synchronized Request take() {
        while (count <= 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Request request = requests[head];
        this.head = (this.head + 1) % this.requests.length;
        this.count--;
        this.notifyAll();
        return request;
    }
}
