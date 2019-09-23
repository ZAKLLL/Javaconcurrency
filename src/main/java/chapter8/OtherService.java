package chapter8;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-05-09 14:45
 **/
public class OtherService {

    private final Object obj = new Object();

    private DeadLock deadLock;

    void setDeadLock(DeadLock deadLock) {
        this.deadLock = deadLock;
    }

    void s1() {
        synchronized (obj) {
            System.out.println("S1===========================");
        }
    }

    void s2() {
        synchronized (obj) {
            System.out.println("S2========================");
            deadLock.m2();
        }
    }
}
