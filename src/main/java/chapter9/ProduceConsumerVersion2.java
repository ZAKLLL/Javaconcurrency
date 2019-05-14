package chapter9;

import sun.awt.windows.ThemeReader;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-05-10 15:35
 **/
public class ProduceConsumerVersion2 {
    private int i = 0;
    private final Object LOCK = new Object();
    private volatile Boolean isProduced = false;

    void produce() {
        synchronized (LOCK) {
            if (isProduced) {
                try {
                    LOCK.wait(); //释放锁，不再生产，让消费者能够持有锁,并且等待被notify唤醒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println(Thread.currentThread().getName() + "->" + ++i);
                isProduced = true;
                LOCK.notify();  //通知消费者可以继续消费
            }
        }
    }

    void consume() {
        synchronized (LOCK) {
            if (isProduced) {
                System.out.println(Thread.currentThread().getName() + "->" + i);
                isProduced = false;
                LOCK.notify();  //通知生产者可以继续设生产了
            } else {
                try {
                    LOCK.wait(); //无可消费的商品，释放锁，并且等待下一次唤醒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ProduceConsumerVersion2 pc = new ProduceConsumerVersion2();
        new Thread("P") {
            @Override
            public void run() {
                while (true) {
                    pc.produce();
                }
            }
        }.start();
        new Thread("P2") {
            @Override
            public void run() {
                while (true) {
                    pc.produce();
                }
            }
        }.start();
        new Thread("C") {
            @Override
            public void run() {
                while (true) {
                    pc.consume();
                }
            }
        }.start();
        new Thread("C2") {
            @Override
            public void run() {
                while (true) {
                    pc.consume();
                }
            }
        }.start();

    }
}
