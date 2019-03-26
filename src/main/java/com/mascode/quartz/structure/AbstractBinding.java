package com.mascode.quartz.structure;

import org.quartz.*;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @Author mazijun@58.com
 */
public abstract class AbstractBinding implements JobBinding {

    public SchedulerTask buildSchedulerTask() {
        SchedulerTask schedulerTask = new SchedulerTask();
        JobDataMap jobDataMap = buildJobData();
        JobDetail jobDetail = getJobDetail(jobDataMap);
        if (jobDetail == null) {
            return null;
        }
        Trigger trigger = getTrigger(jobDetail);
        if (trigger == null) {
            return null;
        }
        schedulerTask.setJobDetail(jobDetail);
        schedulerTask.setTrigger(trigger);
        return schedulerTask;
    }

    private JobDetail getJobDetail(JobDataMap jobDataMap) {
        JobInfo jobInfo = buildJobInfo();
        if (jobInfo == null) {
            return null;
        }
        JobBuilder jobBuilder = newJob(jobInfo.getJob())
                .withIdentity(jobInfo.getJobKey());
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
        TriggerBuilder triggerBuilder = newTrigger().withIdentity(triggerInfo.getTriggerKey()).withSchedule(triggerInfo.getScheduleBuilder());
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
    protected abstract JobDataMap buildJobData();

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
