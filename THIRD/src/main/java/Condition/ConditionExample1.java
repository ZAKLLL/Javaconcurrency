package Condition;

import java.sql.Time;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-10-29 09:38
 **/
public class ConditionExample1 {

    private final static ReentrantLock lock = new ReentrantLock(true);
    private final static Condition condition = lock.newCondition();
    private static int date = 0;
    private static volatile boolean noUse = true;

    //生产者,在被消费之前不会继续生产
    private static void buildDate() {
        try {
            lock.lock(); //synchronized key word #monitor enter
            while (noUse) {
                condition.await();//monitor.wait
            }
            date++;
            noUse = true;
            System.out.println("生产成功: 总生产数量-->" + date + "  当前生产线程" + Thread.currentThread().getName());
            condition.signal(); //monitor.notify #monitor end
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private static void useDate() {
        try {
            lock.lock();
            while (!noUse) {
                condition.await();
            }
            TimeUnit.SECONDS.sleep(1);
            System.out.println("消费成功: 总消费数量-->" + date + "  当前消费线程" + Thread.currentThread().getName());
            noUse = false;
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        IntStream.range(0, 5).forEach(i -> new Thread(() -> {
            while (true) {
                buildDate();
            }
        }, "生产线程" + i).start());
        IntStream.range(0, 5).forEach(i -> new Thread(() -> {
            while (true) {
                useDate();
            }
        }, "消费线程" + i).start());

    }
}
