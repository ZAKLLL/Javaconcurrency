package chapter4;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-07-14 21:26
 **/
public class ReadWriteLock {
    private int ReadingReaders = 0;
    private int WaitingReaders = 0;
    private int WritingWriters = 0;
    private int WaitingWriters = 0;

    public synchronized void readLock() throws InterruptedException{
        this.WaitingWriters++;
        try {
            while (WritingWriters > 0) {
                this.wait();
            }
            this.ReadingReaders++;
        }finally {
            this.WaitingWriters--;
        }
    }

    public synchronized void readUnlock() {
        this.ReadingReaders--;
        this.notifyAll();
    }

    public synchronized void writeLock(){
        this.WaitingWriters++;
        try {
            while (ReadingReaders > 0 || WritingWriters > 0) {
                this.wait();
            }
            this.WritingWriters++;
        } catch (Exception e) {
            e.printStackTrace();
            this.WritingWriters--;
        }
    }
    public synchronized void writeUnLock(){
        this.WritingWriters--;
        this.notifyAll();
    }
}
