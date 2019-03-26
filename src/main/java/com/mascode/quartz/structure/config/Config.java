package com.mascode.quartz.structure.config;

import com.mascode.quartz.structure.NameSpace;
import com.mascode.quartz.structure.impl.NameSpaceAdapter;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mazijun
 */
@Configuration
public class Config {

    @Bean
    public Scheduler getScheduler() throws SchedulerException {
        return new StdSchedulerFactory("quartz.properties").getScheduler();
    }

    @Bean
    public NameSpace getNamingSpace() {
        return new NameSpaceAdapter();
    }

}
