package chapter8;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-05-09 14:45
 **/
public class DeadLock {
    private OtherService otherService;
    private final Object obj = new Object();

    public DeadLock(OtherService otherService) {
        this.otherService = otherService;
    }

    void m1() {
        synchronized (obj) {
            System.out.println("m1");
            otherService.s1();
        }
    }

    void m2() {
        synchronized (obj) {
            System.out.println("m2");
        }
    }
}
