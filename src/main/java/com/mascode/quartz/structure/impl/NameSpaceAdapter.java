package com.mascode.quartz.structure.impl;

import com.mascode.quartz.structure.NameSpace;
import org.quartz.JobKey;
import org.quartz.TriggerKey;
import org.springframework.util.StringUtils;

/**
 * @author mazijun
 * <p>
 * nameSpace的默认实现，
 * 如果业务没有重写当前适配器那么默认为当前适配器
 * <p>
 * 如果业务需要定义业务自己的命名空间，不采用系统默认的
 * 则 重写NameSpaceAdapter getNameSpace 方法即可
 * 在实现类上添加
 * @Primary
 * @Service 以上两个注解，就可以替换掉当前的默认实现
 */
public class NameSpaceAdapter implements NameSpace {
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

    private String convert(String base) {
        return getNameSpace() + base;
    }

    protected String getNameSpace() {
        return "";
    }
}
