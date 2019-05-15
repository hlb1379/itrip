package cn.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.ParseException;
import java.util.Date;

public class TestMain {


    public static void main(String[] args) throws SchedulerException, InterruptedException, ParseException {
        //创建任务job
        JobDetail job = new JobDetail("myJob", "A", RemindJob.class);

        //创建触发器  trigger
        SimpleTrigger trigger = new SimpleTrigger("myTrigger", SimpleTrigger.REPEAT_INDEFINITELY, 3000);
        //秒 分 时 日 月 周 年
        //中划线 -  代表范围
        //逗号，表示一个列表
        // 斜杆 "/"  代表增量
//        CronTrigger cronTrigger = new CronTrigger("myCronTrigger", "B", "10-20 57 16 * * ?");
        CronTrigger cronTrigger = new CronTrigger("myCronTrigger", "B", "0/10 02 17 * * ?");
        trigger.setStartTime(new Date(System.currentTimeMillis()+1000));

        //创建调度器
        //创建Scheduler工厂SchedulerFactory的实例
        StdSchedulerFactory factory = new StdSchedulerFactory();
        //获取调度器
        Scheduler scheduler = factory.getScheduler();
        //调度器粘合任务和触发器
        scheduler.scheduleJob(job, cronTrigger);
        scheduler.start();
        Thread.sleep(200000);
        scheduler.shutdown();
    }
}