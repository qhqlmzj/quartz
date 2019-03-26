package com.mascode.quartz.structure;

import org.quartz.JobKey;
import org.quartz.TriggerKey;

/**
 * 防止多个调度任务之间命名空间冲突
 *
 * @author mazijun
 */
public interface NameSpace {
    /**
     * 将原生的业务表示转化为具有命名空间的标示
     *
     * @param jobName
     * @param groupName
     * @return
     */
    JobKey convertJobKey(String jobName, String groupName);

    /**
     * 将原生的业务表示转化为具有命名空间的标示
     *
     * @param triggerName
     * @param groupName
     * @return
     */
    TriggerKey convertTriggerKey(String triggerName, String groupName);
}
