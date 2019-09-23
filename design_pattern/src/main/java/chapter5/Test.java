package chapter5;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-08-04 10:12
 **/
public class Test {
    public static void main(String[] args) throws InterruptedException {
        FutureService futureService = new FutureService();
        Future<String> future = futureService.submit(() -> {
            Thread.sleep(11000);
            return "FINISH";
        },System.out::println); //传入回调函数

        System.out.println("------------");
        System.out.println("do other thing");
        Thread.sleep(10000);
        System.out.println("------------");
        System.out.println(future.get());
    }
}
