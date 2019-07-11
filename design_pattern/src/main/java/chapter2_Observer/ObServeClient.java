package chapter2_Observer;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-07-08 21:40
 **/
public class ObServeClient {
    public static void main(String[] args) throws InterruptedException {
        final Subject subject = new Subject();
        new OctalObserve(subject);
        new BinaryObserver(subject);
        System.out.println("----------");

        subject.setState(555);

        Thread.sleep(1000);

        subject.setState(10);

    }
}
