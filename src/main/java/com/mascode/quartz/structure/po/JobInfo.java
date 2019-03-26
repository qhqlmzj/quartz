package com.mascode.quartz.structure.po;

import org.quartz.Job;
import org.quartz.JobKey;

/**
 * @author mazijun@58.com
 * job的基本信息
 */
public class JobInfo {
    /**
     * job 的唯一标示
     */
    private JobKey jobKey;
    /**
     * job需要执行的具体的业务逻辑
     */
    private Class<? extends Job> job;

    public Class<? extends Job> getJob() {
        return job;
    }

    public JobKey getJobKey() {
        return jobKey;
    }

    public void setJob(Class<? extends Job> job) {
        this.job = job;
    }

    public void setJobKey(JobKey jobKey) {
        this.jobKey = jobKey;
    }
}
