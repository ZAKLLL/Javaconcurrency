package StampedLock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @program: javaconcurrency
 * @description: 使用Stamped 进行悲观锁实现
 * @author: ZakL
 * @create: 2019-10-29 14:09
 **/
public class StampedLockExample1 {
    private static final StampedLock lock = new StampedLock();
    private final static List<Long> DATA = new ArrayList<>();

    private static void read() {
        long stamped = -1;
        try {
            stamped = lock.readLock();
            Optional.of(DATA.stream().map(String::valueOf).collect(Collectors.joining("#", "R-", ""))).ifPresent(System.out::println);
        } finally {
            lock.unlockRead(stamped);
        }
    }


    private static void write() {
        long stamped = -1;
        try {
            stamped = lock.writeLock();
            DATA.add(System.currentTimeMillis());
        } finally {
            lock.unlockWrite(stamped);
        }
    }

    public static void main(String[] args) {
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
        excutor.submit(writeRunnable);

    }
}
