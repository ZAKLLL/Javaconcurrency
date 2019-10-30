package StampedLock;

import javax.annotation.processing.SupportedSourceVersion;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @program: javaconcurrency
 * @description: 使用Stamped 进行乐观观锁实现
 * @author: ZakL
 * @create: 2019-10-29 14:09
 **/
public class StampedLockExample2 {
    private static final StampedLock lock = new StampedLock();
    private final static List<Long> DATA = new ArrayList<>();

    private static void read() {

        String result;
        long stamp = lock.tryOptimisticRead(); //non blocking
        result = DATA.stream().map(String::valueOf).collect(Collectors.joining("#", "R-", ""));
        if (!lock.validate(stamp)) { // 如果在读期间发生了修改
            try {
                stamp = lock.readLock();
                result= DATA.stream().map(String::valueOf).collect(Collectors.joining("#", "R-", ""));
            } finally {
                lock.unlockRead(stamp);
            }
        }
        System.out.println(result);
    }


    private static void write() {
        long stamped = -1;
        try {
            stamped = lock.writeLock();
            DATA.add(System.currentTimeMillis());
//            TimeUnit.SECONDS.sleep(1);
        } finally {
            lock.unlockWrite(stamped);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final ExecutorService excutor = Executors.newFixedThreadPool(10);
        Runnable readRunable = () -> {
            while (true) {
                read();
            }
        };
        Runnable writeRunnable = () -> {
            while (true) {
                write();
            }
        };

        IntStream.range(0, 9).forEach(i -> excutor.submit(readRunable));
        TimeUnit.SECONDS.sleep(2);
        System.err.println("----------");
        excutor.submit(writeRunnable);

    }
}
