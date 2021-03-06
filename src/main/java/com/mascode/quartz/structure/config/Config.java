package com.mascode.quartz.structure.config;

import com.mascode.quartz.structure.NameSpace;
import com.mascode.quartz.structure.Quartz;
import com.mascode.quartz.structure.QuartzInitializer;
import com.mascode.quartz.structure.impl.DefaultInitializer;
import com.mascode.quartz.structure.impl.DefaultNameSpace;
import com.mascode.quartz.structure.impl.DefaultQuartz;
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
    public QuartzInitializer getQuartzInitializer() {
        return new DefaultInitializer();
    }

    @Bean
    public NameSpace getNamingSpace() {
        return new DefaultNameSpace();
    }

    @Bean
    public Quartz getQuartz() {
        return new DefaultQuartz();
    }

}
