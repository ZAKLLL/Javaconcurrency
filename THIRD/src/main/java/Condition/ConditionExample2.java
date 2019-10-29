package Condition;

import javafx.beans.binding.When;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * @program: javaconcurrency
 * @description: 多线程消费生产者模型
 * @author: ZakL
 * @create: 2019-10-29 10:38
 **/
public class ConditionExample2 {

    private final static Lock lock = new ReentrantLock();
    private final static Condition PRODUCE_COND = lock.newCondition();
    private final static Condition CONSUME_COND = lock.newCondition();
    private final static LinkedList<Long> TIMESTAMP_POOL = new LinkedList<>();
    private final static int MAX_CAPACITY = 5;


    private static void pruoduce() {
        try {
            lock.lock();
            while (TIMESTAMP_POOL.size() >= MAX_CAPACITY) {
                PRODUCE_COND.await(); //生产者block 等待被消费
            }
            long value = System.currentTimeMillis();
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName() + "-PRODUCE-" + value);
            TIMESTAMP_POOL.addLast(value);
            CONSUME_COND.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private static void consume() {
        try {
            lock.lock();
            while (TIMESTAMP_POOL.isEmpty()) {
                CONSUME_COND.await();
            }
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName()+"Consume:"+TIMESTAMP_POOL.pop());
            PRODUCE_COND.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        IntStream.range(0, 7 ).forEach(i-> new Thread(() -> {
            while (true) {
                pruoduce();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"PRODUCE_THREAD"+i).start());
        IntStream.range(0, 5).forEach(i-> new Thread(() -> {
            while (true) {
                consume();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        },"CONSUME_THREAD"+i).start());

    }
}
