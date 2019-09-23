package chapter14;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-08-17 22:06
 **/

public class MessageHandler {
    private static final Random random = new Random(System.currentTimeMillis());
    private static final Executor executor = Executors.newFixedThreadPool(5);

    public void request(Message message) {
        executor.execute(() -> {
            String value = message.getMessage();
            try {
                Thread.sleep(random.nextInt(1000));
                System.out.println("The Message will be handled by " + Thread.currentThread().getName() + "-------->" + value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public void shutDown() {
        ((ExecutorService) executor).shutdown();
    }

}
