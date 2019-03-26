package com.mascode.quartz.logic;

import com.mascode.quartz.structure.QuartzInitializer;
import org.quartz.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainNew {

    private static ApplicationContext applicationContext;

    public static void main(String args[]) throws SchedulerException {
        ApplicationContext context = new AnnotationConfigApplicationContext(
                "com");
        applicationContext = context;
        QuartzInitializer.initQuartz(context);
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
