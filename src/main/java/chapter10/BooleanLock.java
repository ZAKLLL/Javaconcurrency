package chapter10;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-05-12 11:11
 **/
public class BooleanLock implements Lock {

    //true indicated the lock have been token
    //false indicated the lock is free(can be token)
    private boolean initValue;

    private Collection<Thread> blockedTheadCollection = new ArrayList<>();

    private Thread currentThread;

    public BooleanLock() {
        this.initValue = false;
    }

    @Override
    public synchronized void lock() throws InterruptedException {
        while (initValue) {
            blockedTheadCollection.add(Thread.currentThread());
            this.wait();
        }
        blockedTheadCollection.remove(Thread.currentThread());
        currentThread = Thread.currentThread();
        //表明锁已经被拿了
        this.initValue = true;
    }

    @Override
    public synchronized void lock(long mills) throws InterruptedException, TimeOutException {
        if (mills <= 0) lock();
        long hasRemaining = mills;
        long endTime = System.currentTimeMillis() + mills;
        while (initValue) {
            if (hasRemaining <= 0) throw new TimeOutException("time out");
            blockedTheadCollection.add(Thread.currentThread());
            //休眠时间超过mills就会主动唤醒，寻找锁，也可以被notifyAll唤醒
            this.wait(mills);
            hasRemaining =  endTime-System.currentTimeMillis() ;
        }

        this.initValue = true;
        this.currentThread = Thread.currentThread();
    }
    @Override
    public synchronized void unlock() {
        //阻止非启动线程释放锁
        if (Thread.currentThread() == currentThread) {
            this.initValue = false;
            System.out.println(Thread.currentThread() + "release the lock monitor");
            this.notifyAll();
        }
    }
    @Override
    public Collection<Thread> getBlockedThread() {
        //阻止被修改
        return Collections.unmodifiableCollection(blockedTheadCollection);
    }

    @Override
    public int getBlockSedSize() {
        return blockedTheadCollection.size();
    }
}
