package com.mascode.demo1;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.SimpleTriggerImpl;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

public class QuartzTest {


    public static void main(String[] args) {

        try {
            // Grab the Scheduler instance from the Factory
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();


            // define the job and tie it to our com.mascode.demo1.HelloJob class
            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put("key", "value");
            JobDetail job = newJob(HelloJob.class)
                    .withIdentity("job1", "group1").storeDurably().setJobData(jobDataMap)
                    .build();


            JobDetail job1 = newJob(HelloJob.class)
                    .withIdentity("job1", "group1").storeDurably()
                    .build();


            // Trigger the job to run now, and then repeat every 40 seconds
            Trigger trigger = newTrigger()
                    .withIdentity("trigger1", "group1")
                    .forJob(job1)
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(6)
                            .repeatForever())
                    .build();


            // Trigger the job to run now, and then repeat every 40 seconds
            Trigger trigger2 = newTrigger()
                    .withIdentity("trigger2", "group1")
                    .startNow()
                    .forJob(job1)
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(5)
                            .repeatForever())
                    .build();

            // Tell quartz to schedule the job using our trigger
            //actually is the job add by scheduler
            scheduler.addJob(job, true);
            scheduler.scheduleJob(trigger);
            scheduler.scheduleJob(trigger2);


            // and start it off
            scheduler.start();

            try {
                Thread.sleep(10 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //update old trigger
            //get old trigger by triggerKey'
            //update the old trigger
            //reScheduler the job by updated trigger
            SimpleTrigger simpleTrigger = (SimpleTriggerImpl) scheduler.getTrigger(new TriggerKey("trigger1", "group1"));
            ((SimpleTriggerImpl) simpleTrigger).setRepeatInterval(500);

            scheduler.rescheduleJob(new TriggerKey("trigger1", "group1"), simpleTrigger);

            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            //remove job and trigger
            //pause the trigger
            //remove the trigger
            //delete job
            System.out.println("before:");


            System.out.println(scheduler.getTrigger(new TriggerKey("trigger1", "group1")));
            System.out.println(scheduler.getJobDetail(new JobKey("job1", "group1")));

            scheduler.pauseTrigger(new TriggerKey("trigger1", "group1"));
            scheduler.unscheduleJob(new TriggerKey("trigger1", "group1"));
            scheduler.deleteJob(new JobKey("job1", "group1"));


            System.out.println("after:");
            System.out.println(scheduler.getTrigger(new TriggerKey("trigger1", "group1")));
            System.out.println(scheduler.getJobDetail(new JobKey("job1", "group1")));


            scheduler.shutdown();

        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }
}
