import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeSet;
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

        Map<String, String> map = new HashMap<>();
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

    public int maxProfit(int[] prices) {
        if (prices.length <= 1) return 0;
        int[][] dp = new int[prices.length][2]; //dp[][0]表示未持有 dp[][1]表示持有

        for (int i = 0; i < prices.length; i++) {
            if (i == 0) {
                dp[0][0] = 0;
                dp[0][1] = Integer.MIN_VALUE; //表示不可能持有
            }
            int temp = dp[i - 1][0];
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], temp - prices[i]);
        }
        return dp[prices.length - 1][0];
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

