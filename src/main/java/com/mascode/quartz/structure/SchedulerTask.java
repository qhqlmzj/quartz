package com.mascode.quartz.structure;

import org.quartz.JobDetail;
import org.quartz.Trigger;

/**
 * @author mazijun@58.com
 */
public class SchedulerTask {
    /**
     * job详情
     */
    private JobDetail jobDetail;
    /**
     * 触发job逻辑的触发器
     */
    private Trigger trigger;

    public JobDetail getJobDetail() {
        return jobDetail;
    }

    public Trigger getTrigger() {
        return trigger;
    }

    public void setJobDetail(JobDetail jobDetail) {
        this.jobDetail = jobDetail;
    }

    public void setTrigger(Trigger trigger) {
        this.trigger = trigger;
    }
}
