package chapter9;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @program: javaconcurrency
 * @description: 吧台模型
 * @author: ZakL
 * @create: 2019-08-06 21:52
 **/
public class BalkingData {
    private final String fileName;
    private String content;
    private Boolean changed;

    public BalkingData(String fileName, String content, Boolean changed) {
        this.fileName = fileName;
        this.content = content;
        this.changed = changed;
    }

    public synchronized void change(String content) {
        this.content = content;
        this.changed = true;
    }

    public synchronized void save() throws Exception {
        if (!changed) {
            return;
        }
        dosave();
        this.changed = false;
    }


    private void dosave() throws IOException {
        System.out.println(Thread.currentThread().getName() + " --->do save calls");
        try (Writer writer = new FileWriter(fileName, true)) {
            writer.write(content);
            writer.write("\n");
            writer.flush();
        }
    }
}
