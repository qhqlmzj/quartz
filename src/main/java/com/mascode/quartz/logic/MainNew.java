package com.mascode.quartz.logic;

import com.mascode.quartz.structure.QuartzInitializer;
import com.mascode.quartz.structure.impl.DefaultInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainNew {


    public static void main(String args[]) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(
                "com");
        QuartzInitializer quartzInitializer = context.getBean(DefaultInitializer.class);
        quartzInitializer.initQuartz();
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
