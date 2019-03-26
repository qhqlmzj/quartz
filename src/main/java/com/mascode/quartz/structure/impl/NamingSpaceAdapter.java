package com.mascode.quartz.structure.impl;

import com.mascode.quartz.structure.NamingSpace;
import org.quartz.JobKey;
import org.quartz.TriggerKey;
import org.springframework.util.StringUtils;

/**
 * @author mazijun
 */
public class NamingSpaceAdapter implements NamingSpace {
    public final JobKey convertJobKey(String jobName, String groupName) {
        if (StringUtils.isEmpty(jobName) || StringUtils.isEmpty(groupName)) {
            return null;
        }
        return new JobKey(convert(jobName), convert(groupName));
    }

    public final TriggerKey convertTriggerKey(String triggerName, String groupName) {
        if (StringUtils.isEmpty(triggerName) || StringUtils.isEmpty(groupName)) {
            return null;
        }
        return new TriggerKey(convert(triggerName), convert(groupName));
    }

    protected String convert(String base) {
        return "def:" + base;
    }

}
