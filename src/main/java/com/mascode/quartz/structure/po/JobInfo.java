package com.mascode.quartz.structure.po;

import org.quartz.Job;

/**
 * @author mazijun@58.com
 * job的基本信息
 */
public class JobInfo {
    /**
     * job 的唯一标示
     */
    private String jobName;
    private String groupName;
    /**
     * job需要执行的具体的业务逻辑
     */
    private Class<? extends Job> job;

    public JobInfo(String jobName, String groupName) {
        this.jobName = jobName;
        this.groupName = groupName;
    }

    public Class<? extends Job> getJob() {
        return job;
    }

    public void setJob(Class<? extends Job> job) {
        this.job = job;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }
}
