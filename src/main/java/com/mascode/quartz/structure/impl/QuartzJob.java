package com.mascode.quartz.structure.impl;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 需要由具体的业务去实现executeJob方法，
 * 这里面是调度触发之后需要执行的具体的业务逻辑
 * 业务必须去实现该方法
 *
 * @author mazijun
 */
public abstract class QuartzJob implements Job {
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //before execute logic
        try {
            beforeExecute(context);
        } catch (Exception e) {
            error(e);
        }
        //really logic
        executeJob(context);
        //after execute logic
        try {
            afterExecute(context);
        } catch (Exception e) {
            error(e);
        }
    }

    private void error(Exception e) {

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
