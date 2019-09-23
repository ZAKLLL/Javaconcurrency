package chapter8;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-05-10 14:36
 **/
public class DeadLockTest {
    public static void main(String[] args) {
        OtherService otherService = new OtherService();
        DeadLock deadLock = new DeadLock(otherService);
        otherService.setDeadLock(deadLock);

        new Thread(() -> {
            while (true) {
                deadLock.m1();
            }
        }).start();
        new Thread(() -> {
            while (true) {
                otherService.s2();
            }
        }).start();

    }
}
