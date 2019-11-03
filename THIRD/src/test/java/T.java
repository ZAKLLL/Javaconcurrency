/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-09-26 17:34
 **/
public class T {
    public void test() {
        int a = 8;
        while ((a-=3)>0);
        System.out.println("a->:"+a);
    }
    public static void main(String[] args) {
        for (int i = 0; i < 50_0000; i++) {
            new T().test();
        }
    }
}
