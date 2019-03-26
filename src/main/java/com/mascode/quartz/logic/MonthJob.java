package com.mascode.quartz.logic;

import com.mascode.quartz.structure.AbstractBinding;
import com.mascode.quartz.structure.po.JobInfo;
import com.mascode.quartz.structure.annotation.SchedulerBinding;
import com.mascode.quartz.structure.po.TriggerInfo;
import org.quartz.JobDataMap;

@SchedulerBinding
public class MonthJob extends AbstractBinding {
    @Override
    protected JobDataMap buildJobData() {
        return null;
    }

    @Override
    protected JobInfo buildJobInfo() {
        return null;
    }

    @Override
    protected TriggerInfo buildTriggerInfo() {
        return null;
    }
}
