package concurrency1;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-05-03 09:30
 **/
public abstract class TemplateMethod {

    public final void print(String message) {
        System.out.println("-----------");
        wrapPrint(message);
        System.out.println("-----------");
    }
    protected abstract void wrapPrint(String message);
}
