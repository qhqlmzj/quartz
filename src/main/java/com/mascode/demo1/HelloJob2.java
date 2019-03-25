package com.mascode.demo1;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class HelloJob2 implements Job {

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("hello job2");
    }
}
