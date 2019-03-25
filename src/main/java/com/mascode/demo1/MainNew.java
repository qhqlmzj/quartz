package com.mascode.demo1;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class MainNew {
    public static void main(String args[]) throws SchedulerException {
        initQuartz();
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void initQuartz() throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        List<SchedulerTask> schedulerTasks = getSchedulerTasks();
        if (!CollectionUtils.isEmpty(schedulerTasks)) {
            for (SchedulerTask schedulerTask : schedulerTasks) {
                initOneJob(scheduler, schedulerTask);
            }
        }
        scheduler.start();
    }

    private static void initOneJob(Scheduler scheduler, SchedulerTask schedulerTask) throws SchedulerException {
        JobDetail jobDetail = schedulerTask.getJobDetail();
        Trigger trigger = schedulerTask.getTrigger();
        scheduler.addJob(jobDetail, true);
        scheduler.scheduleJob(trigger);
    }

    private static List<SchedulerTask> getSchedulerTasks() {
        return AbstractBinding.getSchedulerTaskList();
    }
}
