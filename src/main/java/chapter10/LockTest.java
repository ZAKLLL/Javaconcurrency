package chapter10;

import chapter10.Lock.TimeOutException;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-05-12 11:28
 **/
public class LockTest {
    public static void main(String[] args) {
        final BooleanLock booleanLock = new BooleanLock();
        Stream.of("T1", "T2", "T3", "T3").forEach(it -> new Thread(it) {
            @Override
            public void run() {
                try {
                    booleanLock.lock(10_000L);
                    Optional.of(Thread.currentThread().getName() + "->hold the lock ,and begin to work").ifPresent(System.out::println);
                    work();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (TimeOutException e) {
                    System.out.println(Thread.currentThread()+"-->"+e.getMessage());
                } finally {
                    booleanLock.unlock();
                }
            }
        }.start());
    }
    private static void work() throws InterruptedException {
        Optional.of(Thread.currentThread().getName() + "->isWorking...").ifPresent(System.out::println);
        Thread.sleep(5_000);
    }
}
