package chapter4;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-07-14 22:02
 **/
public class ReaderThread extends Thread {
    private final ShareDate shareDate;

    public ReaderThread(ShareDate shareDate) {
        this.shareDate = shareDate;
    }

    @Override
    public void run() {
        try {
            while (true) {
                char[] readBuf = shareDate.read();
                System.out.println(Thread.currentThread().getName() + " reads " + String.valueOf(readBuf));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
