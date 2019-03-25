package com.mascode.demo1;

import org.quartz.Job;
import org.quartz.JobKey;

public class JobInfo {
    private JobKey jobKey;
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
