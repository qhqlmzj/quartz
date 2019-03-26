package com.mascode.quartz.logic;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class Say implements Job {
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("say");
    }
}
