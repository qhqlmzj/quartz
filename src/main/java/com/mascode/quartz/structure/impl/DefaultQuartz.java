package com.mascode.quartz.structure.impl;

import com.mascode.quartz.structure.Quartz;
import com.mascode.quartz.structure.po.SchedulerTask;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author mazijun
 */
@Service
public class DefaultQuartz implements Quartz {

    @Autowired
    private Scheduler scheduler;

    public void shutDown() throws Exception {
        scheduler.shutdown();
    }

    public void standBy() throws Exception {
        scheduler.standby();
    }

    public void deleteTrigger(String triggerName, String groupName) throws Exception {
        TriggerKey triggerKey = new TriggerKey(triggerName, groupName);
        scheduler.pauseTrigger(triggerKey);
        scheduler.unscheduleJob(triggerKey);
    }

    public void deleteJob(String jobName, String groupName) throws Exception {
        JobKey jobKey = new JobKey(jobName, groupName);
        scheduler.deleteJob(jobKey);
    }

    public void blinding(SchedulerTask schedulerTask) throws Exception {
        Trigger trigger = schedulerTask.getTrigger();
        JobDetail jobDetail = schedulerTask.getJobDetail();
        scheduler.scheduleJob(jobDetail, trigger);
    }
}
