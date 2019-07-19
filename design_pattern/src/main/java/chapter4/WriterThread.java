package chapter4;

import java.util.Random;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-07-14 21:48
 **/
public class WriterThread extends Thread {

    private static final Random random = new Random(System.currentTimeMillis());
    private final ShareDate shareDate;
    private final String filler;
    private int index = 0;

    public WriterThread(ShareDate shareDate, String filler) {
        this.shareDate = shareDate;
        this.filler = filler;
    }


    @Override
    public void run() {
        try {

            while (true) {
                char c = nextChar();
                shareDate.write(c);
                Thread.sleep(random.nextInt(1000));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private char nextChar() {
        char c = filler.charAt(index);
        index++;
        if (index >= filler.length()) {
            index = 0;
        }
        return c;
    }
}
