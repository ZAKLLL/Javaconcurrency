package atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: javaconcurrency
 * @description: 比较锁
 * @author: ZakL
 * @create: 2019-09-23 15:00
 **/
public class CompareAndSetLock {
    private final AtomicInteger a = new AtomicInteger(0);

    private Thread localThread;

    void trylock() throws GetLockException {
        boolean b = a.compareAndSet(0, 1);

        if (!b) {
             throw new GetLockException("Get Lock Failed");
        } else {
            localThread = Thread.currentThread();
            System.out.println(Thread.currentThread().getName() + "Get The Locked");
        }
    }

    void unlock() {
        if (a.get() == 0) {
            return;
        }
        if (localThread == Thread.currentThread()) {
            a.compareAndSet(1, 0);
            System.out.println(Thread.currentThread().getName() + "Release The Lock");
        }
    }
}
