import java.util.HashMap;
import java.util.Hashtable;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-08-30 22:05
 **/
public class Test {
    public static String output ="";
    public static void foo(int i){
        try{
            if(i == 1){
                throw new Exception();
            }
        }catch(Exception e){
            output += "2";
        }finally{
            output += "3";
        }
        output += "4";
    }

    public static void main(String[] args) {
        foo(0);
        foo(1);
        System.out.println(output);
    }
}
