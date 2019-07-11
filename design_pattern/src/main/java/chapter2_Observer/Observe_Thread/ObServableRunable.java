package chapter2_Observer.Observe_Thread;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-07-08 22:07
 **/
public abstract class ObServableRunable implements Runnable {
    final protected LifeCycleListener lifeCycleListener;

    public ObServableRunable(LifeCycleListener lifeCycleListener) {
        this.lifeCycleListener = lifeCycleListener;
    }

    protected void notifyChange(final RunableEvent runableEvent) {
        lifeCycleListener.onEvent(runableEvent);
    }

    public enum RunableState {
        RUNNING, ERROR, DONE
    }

    public static class RunableEvent {
        private final RunableState state;
        private final Thread thread;
        private final Throwable cause;

        public RunableEvent(RunableState state, Thread thread, Throwable cause) {
            this.state = state;
            this.thread = thread;
            this.cause = cause;
        }

        public RunableState getState() {
            return state;
        }

        public Thread getThread() {
            return thread;
        }

        public Throwable getCause() {
            return cause;
        }
    }


}
