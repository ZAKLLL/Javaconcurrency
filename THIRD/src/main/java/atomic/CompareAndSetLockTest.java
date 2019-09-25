package atomic;

import java.util.stream.IntStream;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-09-23 15:04
 **/
public class CompareAndSetLockTest {
    public static void main(String[] args) {
        CompareAndSetLock compareAndSetLock = new CompareAndSetLock();
        IntStream.range(0, 4).forEach(i -> new Thread(() -> {
            try {
                compareAndSetLock.trylock();
                System.out.println(Thread.currentThread().getName() + "IS Doing ... ");
                Thread.sleep(1000L);
                System.out.println("Work Done");
            } catch (GetLockException | InterruptedException e) {
                e.printStackTrace();
            } finally {
                compareAndSetLock.unlock();
            }
        }).start());
    }
}
