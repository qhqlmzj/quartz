package com.mascode.quartz.logic;

import com.mascode.quartz.structure.impl.JobBindingAdapter;
import com.mascode.quartz.structure.po.JobInfo;
import com.mascode.quartz.structure.annotation.SchedulerBinding;
import com.mascode.quartz.structure.po.TriggerInfo;

@SchedulerBinding
public class MonthJob extends JobBindingAdapter {

    @Override
    protected JobInfo buildJobInfo() {
        return null;
    }

    @Override
    protected TriggerInfo buildTriggerInfo() {
        return null;
    }
}
