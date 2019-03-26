package com.mascode.quartz.structure;

import org.springframework.context.ApplicationContext;

/**
 * @author mazijun
 */
public interface QuartzInitializer {

    /**
     * 初始化调度器
     * <p>
     * 会扫描所有的绑定实现类，并且会进行对应的trigger与jobDetail的注册
     * <p>
     * 1. 实现JobBindingAdapter
     * 2. 在具体的实现类上面添加@SchedulerBinding注解
     * 3. 重写buildJobInfo 与 buildTriggerInfo 方法（必须）
     * 4. buildJobData 方法非必须实现，看具体业务场景
     *
     * @param
     * @throws Exception
     */
    void initQuartz() throws Exception;
}
