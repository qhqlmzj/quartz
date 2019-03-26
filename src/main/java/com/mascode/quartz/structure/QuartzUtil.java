package com.mascode.quartz.structure;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzUtil {
    public static void shutDown() throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.shutdown();
    }

    public static void standBy() throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.standby();
    }

    public static void deleteTrigger(TriggerKey triggerKey) throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.pauseTrigger(triggerKey);
        scheduler.unscheduleJob(triggerKey);
    }

    public static void deleteJob(JobKey jobKey) throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.deleteJob(jobKey);
    }
}
