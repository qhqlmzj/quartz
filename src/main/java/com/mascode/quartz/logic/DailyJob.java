package com.mascode.quartz.logic;

import com.mascode.quartz.structure.impl.AbstractBinding;
import com.mascode.quartz.structure.po.JobInfo;
import com.mascode.quartz.structure.annotation.SchedulerBinding;
import com.mascode.quartz.structure.po.TriggerInfo;
import org.quartz.*;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

@SchedulerBinding(des = "每日调度")
public class DailyJob extends AbstractBinding {

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
