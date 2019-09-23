package chapter6;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-08-04 22:18
 **/
public class SuspensionClient {
    public static void main(String[] args) throws InterruptedException {
        final RequestQueue queue = new RequestQueue();
        new ClientThread(queue,"Alex").start();
        Thread Server= new ServerThread(queue);
        Server.start();
//        Server.join();
        Thread.sleep(10000);

        ((ServerThread) Server).close();

        }
}
