package com.mascode.quartz.structure.impl;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public abstract class QuartzJob implements Job {
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //before execute logic
        beforeExecute(context);
        //really logic
        executeJob(context);
        //after execute logic
        afterExecute(context);
    }

    protected void beforeExecute(JobExecutionContext context) {
        System.out.println("before");
    }

    protected void afterExecute(JobExecutionContext context) {
        System.out.println("after");
    }

    /**
     * 真真的有业务实现的调度的具体逻辑
     *
     * @param context
     * @throws JobExecutionException
     */
    protected abstract void executeJob(JobExecutionContext context) throws JobExecutionException;
}
