package chapter3;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-07-11 08:56
 **/
public class Gate {

    private int count = 0;
    private String name;
    private String address;


     synchronized void pass(String name, String address) {
        count++;
        this.name = name;
        this.address = address;
        verify();
    }

    private void  verify() {
        if (name.charAt(0) != address.charAt(0)) {
            System.out.println("broken----------->" + toString());
        }
    }

    @Override
    public String toString() {
        return "No." + count + name + " from ->" + address;
    }
}
