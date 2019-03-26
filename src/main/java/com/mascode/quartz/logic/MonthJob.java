package com.mascode.quartz.logic;

import com.mascode.quartz.structure.impl.AbstractBinding;
import com.mascode.quartz.structure.po.JobInfo;
import com.mascode.quartz.structure.annotation.SchedulerBinding;
import com.mascode.quartz.structure.po.TriggerInfo;

@SchedulerBinding
public class MonthJob extends AbstractBinding {

    @Override
    protected JobInfo buildJobInfo() {
        return null;
    }

    @Override
    protected TriggerInfo buildTriggerInfo() {
        return null;
    }
}
