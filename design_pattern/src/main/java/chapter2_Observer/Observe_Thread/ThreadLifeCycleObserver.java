package chapter2_Observer.Observe_Thread;

import java.util.List;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-07-08 22:13
 **/
public class ThreadLifeCycleObserver implements LifeCycleListener {
    private final Object lock = new Object();

    public void concurrencyQuery(List<String> ids) {
        if (ids == null || ids.isEmpty())
            return;
        ids.forEach(id -> {
            new Thread(new ObServableRunable(this) {
                @Override
                public void run() {
                    try {
                        notifyChange(new RunableEvent(RunableState.RUNNING, Thread.currentThread(), null));
                        System.out.println(id + "----is Query");
                        Thread.sleep(1000);
                        notifyChange(new RunableEvent(RunableState.DONE, Thread.currentThread(), null));
                    } catch (InterruptedException e) {
                        notifyChange(new RunableEvent(RunableState.ERROR, Thread.currentThread(), e));
                        e.printStackTrace();
                    }
                }
            }).start();
        });
    }

    @Override
    public void onEvent(ObServableRunable.RunableEvent runableEvent) {
        synchronized (lock) {
            System.out.println("Thread " + runableEvent.getThread().getName() + runableEvent.getState());
        }
        if (runableEvent.getCause() != null) {
            runableEvent.getCause().printStackTrace();
        }
    }
}
