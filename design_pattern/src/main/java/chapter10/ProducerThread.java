package chapter10;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-08-06 22:40
 **/
public class ProducerThread extends Thread {
    private final MessageQueue messageQueue;

    private static AtomicInteger count = new AtomicInteger(0);

    public ProducerThread(MessageQueue messageQueue, int seq) {
        super("Producer--->" + seq );
        this.messageQueue = messageQueue;
    }


    @Override
    public void run() {
        while (true) {
            try {
                messageQueue.put(new Message("Message from " + Thread.currentThread().getId() + "第" + count.getAndIncrement()));
                System.out.println(Thread.currentThread().getName()+"is Producing message ");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
