package com.mascode.demo1;

/**
 * @author mazijun@58.com
 */
public interface JobBinding {

    /**
     * 构建一对绑定关系的trigger 和 jobDetail
     * 用于调度
     *
     * @return
     */
    SchedulerTask buildSchedulerTask();

}
