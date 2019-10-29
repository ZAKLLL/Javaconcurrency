package Semaphone;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-10-27 11:12
 **/
public class SemaphoreExample2 {
    public static void main(String[] args) throws InterruptedException {
        final Semaphore semaphore = new Semaphore(3);
        IntStream.range(0,3).forEach(i-> new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName()+"in");
                    try {
                        semaphore.acquire(2); //获取两个信号量
                        System.out.println(Thread.currentThread().getName()+"Get 2 permits Successfully");
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        semaphore.release(2);
                    }
                    System.out.println(Thread.currentThread().getName()+"out");
                }
        ).start());

//        while (true) {
//            System.out.println("availablePermits:"+semaphore.availablePermits());
//            System.out.println("QueueLength(Blocking Threads):"+semaphore.getQueueLength());//blocking Thread
//            TimeUnit.SECONDS.sleep(3);
//        }
    }
}
