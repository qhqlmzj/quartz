package com.mascode.quartz.structure;

import org.quartz.JobKey;
import org.quartz.TriggerKey;

public interface NamingSpace {
    JobKey convertJobKey(String jobName, String groupName);

    TriggerKey convertTriggerKey(String triggerName, String groupName);
}
