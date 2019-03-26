package com.mascode.quartz.logic;

import com.mascode.quartz.structure.impl.JobBindingAdapter;
import com.mascode.quartz.structure.po.JobInfo;
import com.mascode.quartz.structure.annotation.SchedulerBinding;
import com.mascode.quartz.structure.po.TriggerInfo;
import org.quartz.JobKey;
import org.quartz.TriggerKey;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

public class MonthJob extends JobBindingAdapter {

    @Override
    protected JobInfo buildJobInfo() {
        JobInfo jobInfo = new JobInfo();
        jobInfo.setJob(Say.class);
        return jobInfo;
    }

    @Override
    protected TriggerInfo buildTriggerInfo() {
        TriggerInfo triggerInfo = new TriggerInfo();
        triggerInfo.setScheduleBuilder(simpleSchedule().withIntervalInSeconds(1).repeatForever());
        return triggerInfo;
    }
}
