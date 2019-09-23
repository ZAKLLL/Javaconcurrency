package chapter16;

import java.util.Random;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-08-26 21:50
 **/
public class TransportThread extends Thread {
    private final Channel channel;
    private final static Random random = new Random(System.currentTimeMillis());

    public TransportThread(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }

    @Override 
    public void run() {
        int i = 0;
        while (true) {
            Request request = new Request(getName(), i);
            this.channel.put(request);
            try {
                Thread.sleep(random.nextInt(1_00));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
    }
}
