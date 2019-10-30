package ForkJoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-10-30 09:49
 **/
public class ForkJoinRecursiveAction {
    private final static int MAX_THRESHOLD = 3;

    private final static AtomicInteger SUM = new AtomicInteger(0);
    private static class CalculateRecursiveAction extends RecursiveAction {

        private final int start;
        private final int end;

        private CalculateRecursiveAction(int start, int end) {
            this.start = start;
            this.end = end;
        }


        @Override
        protected void compute() {
            if (end - start <= MAX_THRESHOLD) {
                SUM.addAndGet(IntStream.rangeClosed(start, end).sum());
            } else {
                int mid = (start + end) / 2;
                CalculateRecursiveAction leftTask = new CalculateRecursiveAction(start, mid);
                CalculateRecursiveAction rightTask = new CalculateRecursiveAction(mid+1, end);
                leftTask.fork();
                rightTask.fork();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.submit(new CalculateRecursiveAction(0, 10000));
        forkJoinPool.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println(SUM.get());
    }
}
