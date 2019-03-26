package com.mascode.quartz.structure.impl;

import com.mascode.quartz.structure.JobBinding;
import com.mascode.quartz.structure.Quartz;
import com.mascode.quartz.structure.QuartzInitializer;
import com.mascode.quartz.structure.annotation.SchedulerBinding;
import com.mascode.quartz.structure.po.SchedulerTask;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mazijun@58.com
 */
@Service
public class DefaultInitializer implements QuartzInitializer {

    private ApplicationContext applicationContext;

    public void initQuartz(ApplicationContext applicationContextO) throws SchedulerException {
        applicationContext = applicationContextO;
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        List<SchedulerTask> schedulerTasks = getSchedulerTasks();
        if (!CollectionUtils.isEmpty(schedulerTasks)) {
            for (SchedulerTask schedulerTask : schedulerTasks) {
                initOneJob(scheduler, schedulerTask);
            }
        }
        scheduler.start();
    }


    private void initOneJob(Scheduler scheduler, SchedulerTask schedulerTask) throws SchedulerException {
        if (schedulerTask == null) {
            log("schedulerTask is null");
        }
        JobDetail jobDetail = schedulerTask.getJobDetail();
        Trigger trigger = schedulerTask.getTrigger();
        scheduler.addJob(jobDetail, true);
        TriggerKey triggerKey = trigger.getKey();
        //检测是否已经存在当前的trigger
        if (scheduler.checkExists(triggerKey)) {
            Trigger triggerOld = scheduler.getTrigger(triggerKey);
            scheduler.rescheduleJob(triggerKey, triggerOld);
        } else {
            scheduler.scheduleJob(trigger);
        }
    }

    private void log(String msg) {
        System.out.println(msg);
    }

    private List<SchedulerTask> getSchedulerTasks() {
        List<SchedulerTask> schedulerTasks = new ArrayList<SchedulerTask>();
        List<JobBinding> jobBindings = getJobBindings();
        for (JobBinding jobBinding : jobBindings) {
            SchedulerTask schedulerTask = jobBinding.buildSchedulerTask();
            if (schedulerTask == null) {
                log("SchedulerTask is null , class name : " + jobBinding.getClass().getName());
                break;
            }
            schedulerTasks.add(schedulerTask);
        }
        return schedulerTasks;
    }

    private List<JobBinding> getJobBindings() {
        List<JobBinding> list = new ArrayList();
        String[] beans = getApplicationContext()
                .getBeanDefinitionNames();
        for (String beanName : beans) {
            Class<?> beanType = getApplicationContext()
                    .getType(beanName);
            SchedulerBinding schedulerBinding = beanType.getAnnotation(SchedulerBinding.class);
            if (schedulerBinding != null) {
                Object obj = getApplicationContext().getBean(beanName);
                if (obj instanceof JobBinding) {
                    list.add((JobBinding) obj);
                }
            }
        }
        return list;
    }

    private ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
