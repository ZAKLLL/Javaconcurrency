package chapter4;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-07-14 22:07
 **/
public class ReaderWriterClient {
    public static void main(String[] args) {
        ShareDate shareDate = new ShareDate(10);
        new ReaderThread(shareDate).start();
        new ReaderThread(shareDate).start();
        new ReaderThread(shareDate).start();
        new ReaderThread(shareDate).start();
        new ReaderThread(shareDate).start();
        new WriterThread(shareDate, "sdadasd").start();
        
    }
}
