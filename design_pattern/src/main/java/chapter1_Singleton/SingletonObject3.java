package chapter1_Singleton;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-07-03 21:51
 **/
public class SingletonObject3 {

    //加入volatile关键字避免出现现空指针异常. 重排序
    private volatile static SingletonObject3 singletonObject3;

    private SingletonObject3() {

    }

    //double—check检查
    public static SingletonObject3 getintances() {
        if (null == singletonObject3) {
            synchronized (SingletonObject3.class) {
                singletonObject3 = new SingletonObject3(); //多线程竞争中可能出现重排序问题，对象未初始化
            }
        }
        return singletonObject3;
    }

}
