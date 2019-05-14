package chapter9;

import java.util.stream.Stream;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-05-10 15:35
 **/
public class ProduceConsumerVersion3 {
    private int i = 0;
    private final Object LOCK = new Object();
    private volatile Boolean isProduced = false;

    void produce() {
        synchronized (LOCK) {
            while (isProduced) {
                try {
                    LOCK.wait(); //释放锁，不再生产，让消费者能够持有锁,并且等待被notify唤醒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "->" + ++i);
            LOCK.notifyAll();  //通知消费者可以继续消费
            isProduced = true;

        }
    }
    void consume() {
        synchronized (LOCK) {
            while (!isProduced) {
                try {
                    LOCK.wait(); //释放锁，不再消费，让生产者能够持有锁,并且等待被notify唤醒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "->" + i);
            isProduced = false;
            LOCK.notifyAll();  //通知生产者可以继续设生产了
        }
    }
    public static void main(String[] args) {
        ProduceConsumerVersion3 pc = new ProduceConsumerVersion3();
        Stream.of("p1", "p2", "p3").forEach(it -> new Thread(it) {
            @Override
            public void run() {
                while (true) {
                    pc.produce();
                }
            }
        }.start());
        Stream.of("c1", "c2", "c3").forEach(it -> new Thread(it) {
            @Override
            public void run() {
                while (true) {
                    pc.consume();
                }
            }
        }.start());
    }
}

