package chapter12;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-08-11 19:57
 **/
public class JdkCountDown {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("准备执行多线程任务");
        final Random random = new Random(System.currentTimeMillis());
        final CountDownLatch latch = new CountDownLatch(5);
        IntStream.rangeClosed(1, 5).forEach(i -> new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "is Working");
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
        }, String.valueOf(i)).start());
        latch.await();
        System.out.println("多线程执行完毕----------");
    }
}
