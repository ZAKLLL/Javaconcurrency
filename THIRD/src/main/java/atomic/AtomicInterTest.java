package atomic;

import cn.hutool.core.collection.ConcurrentHashSet;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-09-23 09:30
 **/
public class AtomicInterTest {
    /**
     * 1.保证内存可见性
     * 2.内存屏障(静止重排序)
     * 3.不能保证原子性
     */
    private volatile int value;

    /**
     * 保证原子性
     */
    private static AtomicInteger val = new AtomicInteger();

    private static Set<Integer> set = new ConcurrentHashSet<>();

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);

        IntStream.rangeClosed(0, 2).forEach(
                i -> new Thread(() -> {
                    int x = 0;
                    while (x < 500) {
                        int andIncrement = val.getAndIncrement();
                        set.add(andIncrement);
                        System.out.println(Thread.currentThread().getName() + ":" + andIncrement);
                        x++;
                        val.getAndAdd(2);
                    }
                    countDownLatch.countDown();
                }).start()
        );
        countDownLatch.await();
        System.out.print("count---->"+set.size());

    }

}
