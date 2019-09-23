package chapter10;

import java.util.stream.IntStream;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-08-06 22:47
 **/
public class ProducerComsumerClient {
    public static void main(String[] args) throws InterruptedException {
        final MessageQueue messageQueue = new MessageQueue();
        IntStream.range(0, 4).forEach(i -> new ProducerThread(messageQueue, i).start());
        IntStream.range(0, 4).forEach(i -> new Thread(() -> {
            while (true) {
                try {
                    Message take = messageQueue.take();
                    System.out.println("Consumer is doing ----->" + take.getData());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start());
        Thread.sleep(50000);

    }
}
