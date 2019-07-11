package chapter2_Observer.Observe_Thread;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-07-08 22:11
 **/
public interface LifeCycleListener {

    void onEvent(ObServableRunable.RunableEvent runableEvent);


}
