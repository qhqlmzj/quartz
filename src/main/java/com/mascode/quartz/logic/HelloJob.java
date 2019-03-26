package com.mascode.quartz.logic;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class HelloJob implements Job {

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("HELLO JOB " + jobExecutionContext.getJobDetail().getJobDataMap().get("key"));
    }
}
