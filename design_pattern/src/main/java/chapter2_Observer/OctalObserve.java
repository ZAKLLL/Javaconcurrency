package chapter2_Observer;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-07-08 21:32
 **/
public class OctalObserve extends Observer {

    public OctalObserve(Subject subject) {
        super(subject);
    }

    @Override
    public void update() {
        System.out.println("Binary String " + Integer.toOctalString(subject.getState()));
    }
}
