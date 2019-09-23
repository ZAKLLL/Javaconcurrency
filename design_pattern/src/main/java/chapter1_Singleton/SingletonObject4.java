package chapter1_Singleton;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-07-03 22:14
 **/
public class SingletonObject4 {

    private SingletonObject4() {

    }

    //使用枚举的方式
    private enum Singleton {
        INSTANCE;

        private final SingletonObject4 singletonObject4;

        Singleton() {
            singletonObject4 = new SingletonObject4();
        }

        public SingletonObject4 getInstance() {
            return singletonObject4;
        }
    }

    public SingletonObject4 getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

}
