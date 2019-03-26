package com.mascode.quartz.logic;

import com.mascode.quartz.structure.impl.QuartzJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class Say extends QuartzJob {
    @Override
    public void executeJob(JobExecutionContext context) throws JobExecutionException {
        System.out.println(context.getJobDetail().getKey());
    }
}
