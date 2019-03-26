package com.mascode.quartz.structure.po;

import org.quartz.ScheduleBuilder;

/**
 * @author mazijun@58.com
 * <p>
 * 触发器的基本信息
 */
public class TriggerInfo {
    /**
     * 触发器的唯一标示
     */
    private String triggerName;
    private String groupName;
    /**
     * 负责具体调度任务的调度触发条件
     */
    private ScheduleBuilder scheduleBuilder;

    public TriggerInfo(String triggerName, String groupName) {
        this.triggerName = triggerName;
        this.groupName = groupName;
    }

    public ScheduleBuilder getScheduleBuilder() {
        return scheduleBuilder;
    }

    public void setScheduleBuilder(ScheduleBuilder scheduleBuilder) {
        this.scheduleBuilder = scheduleBuilder;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getTriggerName() {
        return triggerName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }
}
