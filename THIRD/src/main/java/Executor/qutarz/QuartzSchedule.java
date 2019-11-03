package Executor.qutarz;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @program: javaconcurrency
 * @description: 定时任务
 * @author: ZakL
 * @create: 2019-11-01 11:55
 **/
public class QuartzSchedule {
    public static void main(String[] args) throws SchedulerException {
        JobDetail job = newJob(SimpleJob.class).withIdentity("Job1", "Group1").build();
        Trigger trigger = newTrigger().withIdentity("Trigger1", "group1").withSchedule(cronSchedule("0/5 * * * * ?")).build();
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        scheduler.scheduleJob(job, trigger);
    }
}
