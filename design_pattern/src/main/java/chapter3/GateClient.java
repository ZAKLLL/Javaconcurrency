package chapter3;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-07-11 09:05
 **/
public class GateClient {
    public static void main(String[] args) {
        Gate gate = new Gate();
        User gz = new User("GZ", "GuangZhou", gate);
        User hn = new User("HN", "HuNan", gate);
        User cq = new User("CQ", "ChongQing", gate);
        gz.start();
        hn.start();
        cq.start();
    }
}
