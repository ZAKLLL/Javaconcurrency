package Semaphone;

import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

/**
 * @program: javaconcurrency
 * @description: 使用一个信号量进行锁操作
 * @author: ZakL
 * @create: 2019-10-27 11:02
 **/
public class SemaphoreExample1 {
    public static void main(String[] args) {
        final SemaphoreLock semaphoreLock = new SemaphoreLock();
        IntStream.range(0, 3).forEach(
                i -> new Thread(() -> {
                    System.out.println(Thread.currentThread().getName() + "in");
                    try {
                        semaphoreLock.lock();
                        System.out.println(Thread.currentThread().getName() + "Get the Lock");
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        semaphoreLock.unlock();
                    }
                    System.out.println(Thread.currentThread().getName() + "out");
                }).start()
        );
    }

    static class SemaphoreLock {
        private final Semaphore semaphore = new Semaphore(1);

        public void lock() throws InterruptedException {
            semaphore.acquire();
        }

        public void unlock() {
            semaphore.release();
        }
    }
}
