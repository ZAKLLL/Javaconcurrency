package chapter1_Singleton;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-05-03 09:41
 **/
public class Test  {
    public static void main(String[] args) {
        String a = "dfwe3d35412dsd33d3443fffffsadsasd3313dc32df3453141e12312d21";
        String b = "dfwe3d35412dsd33d3443fffffsadsasd3313dc32df3453141e12312d23";
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
    }


}

class  B implements Runnable {
    public void run() {

    }
}
