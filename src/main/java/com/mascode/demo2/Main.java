package com.mascode.demo2;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String args[]) throws SchedulerException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "spring.xml");
        System.out.println("xxx");

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        System.out.println(scheduler.getJobGroupNames());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
