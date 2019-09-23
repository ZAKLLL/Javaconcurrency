package chapter16;

import java.util.Random;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-08-26 21:02
 **/
public class WorkerThread extends Thread {
    private final Channel channel;
    private final static Random random = new Random(System.currentTimeMillis());
    public WorkerThread(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }

    @Override
    public void run() {
        while (true) {
            channel.take().excute();
            try {
                Thread.sleep(random.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
