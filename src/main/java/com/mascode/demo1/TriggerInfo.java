package com.mascode.demo1;

import org.quartz.ScheduleBuilder;
import org.quartz.TriggerKey;

public class TriggerInfo {
    private TriggerKey triggerKey;
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
