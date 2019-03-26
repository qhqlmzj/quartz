package com.mascode.quartz.structure;

import org.springframework.context.ApplicationContext;

/**
 * @author mazijun
 */
public interface QuartzInitializer {

    /**
     * 初始化调度器
     *
     * @param applicationContextO
     * @throws Exception
     */
    void initQuartz(ApplicationContext applicationContextO) throws Exception;
}
