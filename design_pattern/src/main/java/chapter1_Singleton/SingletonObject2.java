package chapter1_Singleton;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-07-03 21:51
 **/
public class SingletonObject2 {
    private static SingletonObject2 singletonObject2;

    private SingletonObject2() {

    }

    //多线程情况下可能出现重复创建实例的问题 加Synchronized可以避免这个问题，但是会影响性能
    public static SingletonObject2 getSingletonObject2() {
        if (singletonObject2 == null) {
            singletonObject2 = new SingletonObject2();
        }
        return singletonObject2;
    }

}
