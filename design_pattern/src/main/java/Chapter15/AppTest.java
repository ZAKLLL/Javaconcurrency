package Chapter15;

import java.io.IOException;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-08-26 16:07
 **/
public class AppTest {
    public static void main(String[] args) throws InterruptedException, IOException {
        AppServer appServer = new AppServer(8787);
        appServer.start();
        Thread.sleep(10_000L);
        appServer.shutDown();
    }
}
