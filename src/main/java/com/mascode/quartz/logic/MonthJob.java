package com.mascode.quartz.logic;

import com.mascode.quartz.structure.annotation.SchedulerBinding;
import com.mascode.quartz.structure.impl.JobBindingAdapter;
import com.mascode.quartz.structure.po.JobInfo;
import com.mascode.quartz.structure.po.TriggerInfo;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

@SchedulerBinding
public class MonthJob extends JobBindingAdapter {

    @Override
    protected JobInfo buildJobInfo() {
        JobInfo jobInfo = new JobInfo();
        jobInfo.setJob(Say.class);
        jobInfo.setJobName("job2");
        jobInfo.setGroupName("group1");
        return jobInfo;
    }

    @Override
    protected TriggerInfo buildTriggerInfo() {
        TriggerInfo triggerInfo = new TriggerInfo();
        triggerInfo.setTriggerName("trigger2");
        triggerInfo.setGroupName("group");
        triggerInfo.setScheduleBuilder(simpleSchedule().withIntervalInSeconds(1).repeatForever());
        return triggerInfo;
    }
}
