package chapter4;

import java.util.stream.IntStream;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-07-14 21:37
 **/
public class ShareDate {
    private final char[] buffer;
    private final ReadWriteLock lock = new ReadWriteLock();

    public ShareDate(int size) {
        this.buffer = new char[size];
        IntStream.range(0, size).forEach(i -> buffer[i] = '*');
    }

    public char[] read() throws Exception {
        try {
            lock.readLock();
            return this.doRead();
        } finally {
            lock.readUnlock();
        }
    }

    public void write(char c) throws InterruptedException {
        try {
            lock.writeLock();
            this.doWrite(c);
        }finally {
            lock.writeUnLock();
        }
    }

    public void doWrite(char c) {
        IntStream.range(0, buffer.length).forEach(i -> {
            buffer[i] = c;
            this.slowly(10);
        });
    }

    private char[] doRead() {
        char[] newBuf = new char[buffer.length];
        IntStream.range(0, buffer.length).forEach(i -> newBuf[i] = buffer[i]);
        slowly(50);
        return newBuf;
    }

    private void slowly(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
