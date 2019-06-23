package chapter13;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-05-13 10:48
 **/
public class SimpleThreadPool {
    private final int size;

    private final static int DEFAULT_SIZE = 10;

    private static volatile int seq = 0;

    private static String THREAD_PREFIX = "SIMPLE_THREAD_POOL";

    private final static LinkedList<Runnable> TASK_QUEUE = new LinkedList<>();

    private final static ThreadGroup group = new ThreadGroup("Pool_Group");

    private final static ArrayList<WorkerTask> Thread_Queue = new ArrayList<>();

    public SimpleThreadPool() {
        this(DEFAULT_SIZE);
    }

    public SimpleThreadPool(int size) {
        this.size = size;
        init();
    }

    public void submit(Runnable runnable) {
        synchronized (TASK_QUEUE) {
            TASK_QUEUE.addLast(runnable);
            TASK_QUEUE.notifyAll();
        }
    }

    private void init() {
        for (int i = 0; i < size; i++) {
            createworktask();
        }

    }

    private void createworktask() {
        WorkerTask workerTask = new WorkerTask(group, THREAD_PREFIX + (seq++));
        workerTask.start();
        Thread_Queue.add(workerTask);
    }

    private enum TaskState {
        RUNNING, BLOCKED, FREE, DEAD
    }

    private static class WorkerTask extends Thread {
        private volatile TaskState taskState = TaskState.FREE;

        private WorkerTask(ThreadGroup group, String name) {
            super(group, name);
        }

        private TaskState getTaskState() {
            return this.taskState;
        }

        @Override
        public void run() {
            OUTER:
            while (this.taskState != TaskState.DEAD) {
                Runnable runnable;
                synchronized (TASK_QUEUE) {
                    while (TASK_QUEUE.isEmpty()) {
                        try {
                            taskState = TaskState.BLOCKED;
                            TASK_QUEUE.wait();

                        } catch (InterruptedException e) {
                            break OUTER;
                        }
                    }
                }
                runnable = TASK_QUEUE.removeFirst();
                if (runnable != null) {
                    taskState = TaskState.RUNNING;
                    runnable.run();
                    taskState = TaskState.FREE;
                }
            }
        }
    }
}
