package com.mascode.quartz.logic;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainNew {

    public static void main(String args[]) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(
                "com");
    }
}
