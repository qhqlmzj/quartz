package com.mascode.demo2;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyTask {

    @Scheduled(cron = "0/3 * *  * * ? ")
    public void aTask() {
        System.out.println("注解定时任务测试~~");
    }


    @Scheduled(fixedRate = 1000)
    public void b(){
        System.out.println("bbbb~~");

    }

}
