package chapter16;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-08-26 21:01
 **/
public class Request {
    private final String name;
    private final int number;

    public Request(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public void excute() {
        System.out.println(Thread.currentThread().getName() + " executed number" + number + "name" + name);
    }
}
