import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Random;

import static java.lang.System.currentTimeMillis;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-05-05 10:35
 **/
public class Test {
    public static void main(String[] args) throws IOException, InterruptedException {
        long l = currentTimeMillis();
        flag:
        while (true) {
            System.out.println(new Random().nextInt(50));
            Thread.sleep(100);
            if (System.currentTimeMillis() - l > 5 * 1000) {
                break flag;
            }
        }
    }
}
