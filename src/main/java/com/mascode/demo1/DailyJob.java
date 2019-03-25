package com.mascode.demo1;

import org.quartz.*;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

@SchedulerBinding
public class DailyJob extends AbstractBinding {

    private static final DailyJob dailyJob = new DailyJob();

    public static DailyJob getInstance() {
        return dailyJob;
    }

    @Override
    protected JobDataMap buildJobData() {
        return null;
    }

    @Override
    protected JobInfo buildJobInfo() {
        JobInfo jobInfo = new JobInfo();
        jobInfo.setJob(HelloJob.class);
        jobInfo.setJobKey(new JobKey("job1", "group1"));
        return jobInfo;
    }

    @Override
    protected TriggerInfo buildTriggerInfo() {
        TriggerInfo triggerInfo = new TriggerInfo();
        triggerInfo.setTriggerKey(new TriggerKey("trigger1", "group1"));
        triggerInfo.setScheduleBuilder(simpleSchedule().withIntervalInSeconds(6).repeatForever());
        return triggerInfo;
    }

}
