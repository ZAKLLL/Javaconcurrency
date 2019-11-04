import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-09-26 17:34
 **/
public class T {

    public static void main(String[] args) {
        plate<? extends Fruit> plate = new plate();
    }

    static class plate<T> {
        List<T> a = new ArrayList<>();

        public void add(T t) {
            a.add(t);
        }

        public T get() {
            return a.get(0);
        }

    }

    static class Fruit {

    }

    static class Apple extends Fruit {


    }

}


