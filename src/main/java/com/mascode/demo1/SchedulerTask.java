package com.mascode.demo1;

import org.quartz.JobDetail;
import org.quartz.Trigger;

public class SchedulerTask {
    private JobDetail jobDetail;
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
