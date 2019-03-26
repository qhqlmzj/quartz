package com.mascode.quartz.structure.impl;

import com.mascode.quartz.structure.Quartz;
import com.mascode.quartz.structure.po.SchedulerTask;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author mazijun
 */
public class DefaultQuartz implements Quartz {

    @Autowired
    private Scheduler scheduler;

    public void shutDown() throws SchedulerException {
        scheduler.shutdown();
    }

    public void standBy() throws SchedulerException {
        scheduler.standby();
    }

    public void deleteTrigger(String triggerName, String groupName) throws SchedulerException {
        TriggerKey triggerKey = new TriggerKey(triggerName, groupName);
        scheduler.pauseTrigger(triggerKey);
        scheduler.unscheduleJob(triggerKey);
    }

    public void deleteJob(String jobName, String groupName) throws SchedulerException {
        JobKey jobKey = new JobKey(jobName, groupName);
        scheduler.deleteJob(jobKey);
    }

    public void blinding(SchedulerTask schedulerTask) throws SchedulerException {
        Trigger trigger = schedulerTask.getTrigger();
        JobDetail jobDetail = schedulerTask.getJobDetail();
        scheduler.scheduleJob(jobDetail, trigger);
    }
}
