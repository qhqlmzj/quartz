package com.mascode.demo1;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
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
        SchedulerTask schedulerTaskOut = null;
        if (!CollectionUtils.isEmpty(schedulerTasks)) {
            for (SchedulerTask schedulerTask : schedulerTasks) {
                initOneJob(scheduler, schedulerTask);
                schedulerTaskOut = schedulerTask;
            }
        }
        scheduler.start();

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        delete(schedulerTaskOut.getTrigger().getKey());
        dropJob(schedulerTaskOut.getJobDetail().getKey());

    }

    public static void delete(TriggerKey triggerKey) throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.pauseTrigger(triggerKey);
        scheduler.unscheduleJob(triggerKey);
    }

    public static void dropJob(JobKey jobKey) throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.deleteJob(jobKey);
    }

    private static void initOneJob(Scheduler scheduler, SchedulerTask schedulerTask) throws SchedulerException {
        JobDetail jobDetail = schedulerTask.getJobDetail();
        Trigger trigger = schedulerTask.getTrigger();
        scheduler.addJob(jobDetail, true);
        TriggerKey triggerKey = trigger.getKey();
        //检测是否已经存在当前的trigger
        if (scheduler.checkExists(triggerKey)) {
            Trigger triggerOld = scheduler.getTrigger(triggerKey);
            scheduler.rescheduleJob(triggerKey, triggerOld);
        } else {
            scheduler.scheduleJob(trigger);
        }
    }

    private static List<SchedulerTask> getSchedulerTasks() {
        List<SchedulerTask> list = new ArrayList<SchedulerTask>();
        list.add(DailyJob.getInstance().buildSchedulerTask());

        return list;
    }
}
