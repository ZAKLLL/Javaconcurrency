import java.io.InputStream;
import java.util.stream.IntStream;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-07-03 22:25
 **/
public class Test {
    public static void main(String[] args) {
        IntStream.rangeClosed(1, 10).forEach(i -> {

            new Thread(new TestThread(), "线程" + i).start();
        });
    }

    private static int INIT_VALUE = 6;
    private static ThreadLocal<Integer> integerThreadLocal = new MyThreadLocal(INIT_VALUE);


    private static final Object lockObj = new Object();


    private static class TestThread implements Runnable {

        @Override
        public void run() {
//            synchronized (lockObj) {
            System.out.println("线程->        " + Thread.currentThread().getName() + "    INIT_VALUE->   " + integerThreadLocal.get());
            for (int i = 0; i < 10; i++) {
                integerThreadLocal.set(integerThreadLocal.get() == null ? 0 : integerThreadLocal.get() + i);
            }
            System.out.println("线程->        " + Thread.currentThread().getName() + "    UPDATE_VALUE->   " + integerThreadLocal.get());
//            }
        }
    }


    private static class MyThreadLocal extends ThreadLocal<Integer> {
        public MyThreadLocal(int a) {
            this.set(a);
        }

        @Override
        protected Integer initialValue() {
            return 0;
        }
    }
}

