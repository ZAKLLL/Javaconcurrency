package chapter6;

import java.util.Random;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-08-04 22:10
 **/
public class ServerThread extends Thread {
    private final RequestQueue requestQueue;
    private final Random random;
    private volatile boolean closed = false;

    public ServerThread(RequestQueue requestQueue) {
        this.requestQueue = requestQueue;
        this.random = new Random(System.currentTimeMillis());
    }


    @Override
    public void run() {
        while (!closed) {
            Request request = requestQueue.getRequest();
            if (null == request) {
                System.out.println("Received the null");
                continue;
            }
            System.out.println("Server->" + request.getValue());
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void close() {
        this.closed = true;
        this.interrupt();
    }
}
