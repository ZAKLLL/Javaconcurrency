package chapter9;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-05-10 15:22
 **/
public class ProduceConsumerVersion1 {
    private int i = 0;
    private final Object LOCK = new Object();

    void produce() {
        synchronized (LOCK) {
            System.out.println("p->" + ++i);
        }
    }
    void consume() {
        synchronized (LOCK) {
            System.out.println("C->"+i);
        }
    }
    public static void main(String[] args) {
        ProduceConsumerVersion1 pc = new ProduceConsumerVersion1();
        new Thread("P") {
            @Override
            public void run() {
                while (true)
                    pc.produce();
            }
        }.start();
        //无进程之间通信，消费者不知道生产者是否生产，生产者不知道消费者是否消费。双方各做各的
        new Thread("C") {
            @Override
            public void run() {
                while (true)
                    pc.consume();
            }
        }.start();
    }
}
