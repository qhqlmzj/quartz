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
            beforeExecuteIn(context);
        } catch (Exception e) {
            error(e);
        }
        //really logic
        executeJob(context);
        //after execute logic
        try {
            afterExecuteIn(context);
        } catch (Exception e) {
            error(e);
        }
    }

    private void error(Exception e) {

    }

    private void beforeExecuteIn(JobExecutionContext context) {
        beforeExecuteIn0(context);
        //impl by logic
        beforeExecute(context);
    }

    private void beforeExecuteIn0(JobExecutionContext context) {
        System.out.println("before");
    }

    private void afterExecuteIn(JobExecutionContext context) {
        afterExecuteIn0(context);
        //impl by logic
        afterExecute(context);
    }

    private void afterExecuteIn0(JobExecutionContext context) {
        System.out.println("after");
    }

    protected void beforeExecute(JobExecutionContext context) {
        //null impl
    }

    protected void afterExecute(JobExecutionContext context) {
        //null impl
    }

    /**
     * 真真的有业务实现的调度的具体逻辑
     *
     * @param context
     * @throws JobExecutionException
     */
    protected abstract void executeJob(JobExecutionContext context) throws JobExecutionException;
}
