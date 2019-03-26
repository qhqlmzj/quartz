package com.mascode.quartz.structure.impl;

import com.mascode.quartz.structure.JobBinding;
import com.mascode.quartz.structure.NameSpace;
import com.mascode.quartz.structure.po.JobInfo;
import com.mascode.quartz.structure.po.SchedulerTask;
import com.mascode.quartz.structure.po.TriggerInfo;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @Author mazijun@58.com
 */
public abstract class JobBindingAdapter implements JobBinding {

    @Autowired
    private NameSpace namingSpace;

    public final SchedulerTask buildSchedulerTask() {
        SchedulerTask schedulerTask = new SchedulerTask();
        JobDataMap jobDataMap = buildJobData();
        JobDetail jobDetail = getJobDetail(jobDataMap);
        if (checkJobDetail(jobDetail)) {
            return null;
        }
        Trigger trigger = getTrigger(jobDetail);
        if (checkTrigger(trigger)) {
            return null;
        }
        schedulerTask.setJobDetail(jobDetail);
        schedulerTask.setTrigger(trigger);
        return schedulerTask;
    }

    private boolean checkJobDetail(JobDetail jobDetail) {
        return jobDetail == null || jobDetail.getKey() == null || jobDetail.getJobClass() == null;
    }

    private boolean checkTrigger(Trigger trigger) {
        return trigger == null || trigger.getKey() == null || trigger.getScheduleBuilder() == null;
    }

    private JobDetail getJobDetail(JobDataMap jobDataMap) {
        JobInfo jobInfo = buildJobInfo();
        if (jobInfo == null) {
            return null;
        }
        JobKey jobKey = namingSpace.convertJobKey(jobInfo.getJobName(), jobInfo.getGroupName());
        if (jobKey == null) {
            return null;
        }
        JobBuilder jobBuilder = newJob(jobInfo.getJob())
                .withIdentity(jobKey);
        if (jobBuilder == null) {
            return null;
        }
        if (jobDataMap != null) {
            jobBuilder.setJobData(jobDataMap);
        }
        jobBuilder.storeDurably();
        return jobBuilder.build();
    }

    private Trigger getTrigger(JobDetail jobDetail) {
        if (jobDetail == null) {
            return null;
        }
        TriggerInfo triggerInfo = buildTriggerInfo();
        if (triggerInfo == null) {
            return null;
        }
        TriggerKey triggerKey = namingSpace.convertTriggerKey(triggerInfo.getTriggerName(), triggerInfo.getGroupName());
        if (triggerKey == null) {
            return null;
        }
        TriggerBuilder triggerBuilder = newTrigger().withIdentity(triggerKey).withSchedule(triggerInfo.getScheduleBuilder());
        if (triggerBuilder == null) {
            return null;
        }
        triggerBuilder.forJob(jobDetail);
        return triggerBuilder.build();
    }

    /**
     * 构建job的context参数
     *
     * @return
     */
    protected JobDataMap buildJobData() {
        return null;
    }

    /**
     * 构建job的基本信息，包括唯一标示jobKey以及绑定的job逻辑
     *
     * @return
     */
    protected abstract JobInfo buildJobInfo();

    /**
     * 构建trigger的基本信息，包括唯一标示triggerKey以及调度scheduler
     *
     * @return
     */
    protected abstract TriggerInfo buildTriggerInfo();
}
