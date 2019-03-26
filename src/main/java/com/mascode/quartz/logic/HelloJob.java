package com.mascode.quartz.logic;

import com.mascode.quartz.structure.impl.QuartzJob;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class HelloJob extends QuartzJob {

    @Override
    public void executeJob(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("HELLO JOB " + jobExecutionContext.getJobDetail().getKey());
        jobExecutionContext.getJobDetail().getJobDataMap().put("key", "newValue");
        System.out.println("HELLO JOB " + jobExecutionContext.getJobDetail().getJobDataMap().get("key"));
    }

    @Override
    protected void beforeExecute(JobExecutionContext context) {
        System.out.println("xxxxxxx");
    }
}
