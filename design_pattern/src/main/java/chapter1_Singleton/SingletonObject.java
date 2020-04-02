package chapter1_Singleton;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-07-03 22:12
 **/
public class SingletonObject {
    private SingletonObject() {

    }

    private static class InstanceHolder {
        private final static SingletonObject singleton = new SingletonObject();
    }

    public SingletonObject getInstance() {
        return InstanceHolder.singleton;
    }
}
