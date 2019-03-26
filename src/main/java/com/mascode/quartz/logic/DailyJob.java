package com.mascode.quartz.logic;

import com.mascode.quartz.structure.impl.JobBindingAdapter;
import com.mascode.quartz.structure.po.JobInfo;
import com.mascode.quartz.structure.annotation.SchedulerBinding;
import com.mascode.quartz.structure.po.TriggerInfo;
import org.quartz.*;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

@SchedulerBinding(des = "每日调度")
public class DailyJob extends JobBindingAdapter {

    @Override
    protected JobInfo buildJobInfo() {
        JobInfo jobInfo = new JobInfo("job1", "group1");
        jobInfo.setJob(HelloJob.class);
        return jobInfo;
    }

    @Override
    protected TriggerInfo buildTriggerInfo() {
        TriggerInfo triggerInfo = new TriggerInfo("trigger1", "group1");
        triggerInfo.setScheduleBuilder(
                simpleSchedule()
                        .withIntervalInSeconds(6)
                        .repeatForever());
        return triggerInfo;
    }
}
