package Executor.qutarz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.util.concurrent.TimeUnit;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-11-01 11:50
 **/
public class SimpleJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        System.out.println("===================="+System.currentTimeMillis());
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"work done");
    }
}
