package com.mascode.quartz.structure.po;

import org.quartz.ScheduleBuilder;
import org.quartz.TriggerKey;

/**
 * @author mazijun@58.com
 * <p>
 * 触发器的基本信息
 */
public class TriggerInfo {
    /**
     * 触发器的唯一标示
     */
    private TriggerKey triggerKey;
    /**
     * 负责具体调度任务的调度触发条件
     */
    private ScheduleBuilder scheduleBuilder;

    public ScheduleBuilder getScheduleBuilder() {
        return scheduleBuilder;
    }

    public TriggerKey getTriggerKey() {
        return triggerKey;
    }

    public void setScheduleBuilder(ScheduleBuilder scheduleBuilder) {
        this.scheduleBuilder = scheduleBuilder;
    }

    public void setTriggerKey(TriggerKey triggerKey) {
        this.triggerKey = triggerKey;
    }
}
