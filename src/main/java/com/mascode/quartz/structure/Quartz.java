package com.mascode.quartz.structure;


import com.mascode.quartz.structure.po.SchedulerTask;

/**
 * @author mazijun
 */
public interface Quartz {

    /**
     * 停止当前的调度器
     *
     * @throws Exception
     */
    void shutDown() throws Exception;

    /**
     * 暂停当前调度器
     *
     * @throws Exception
     */
    void standBy() throws Exception;

    /**
     * 删除一个调度器的trigger
     *
     * @param triggerName
     * @param groupName
     * @throws Exception
     */
    void deleteTrigger(String triggerName, String groupName) throws Exception;

    /**
     * 删除一个任务
     *
     * @param jobName
     * @param groupName
     * @throws Exception
     */
    void deleteJob(String jobName, String groupName) throws Exception;

    /**
     * 添加新的job
     *
     * @param schedulerTask
     */
    void blinding(SchedulerTask schedulerTask) throws Exception;
}
