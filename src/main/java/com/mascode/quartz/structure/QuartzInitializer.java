package com.mascode.quartz.structure;

import com.mascode.quartz.structure.annotation.SchedulerBinding;
import com.mascode.quartz.structure.po.SchedulerTask;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mazijun@58.com
 */
public class QuartzInitializer {
    private static ApplicationContext applicationContext;


    public static void initQuartz(ApplicationContext applicationContextO) throws SchedulerException {
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


    private static void initOneJob(Scheduler scheduler, SchedulerTask schedulerTask) throws SchedulerException {
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

    private static List<SchedulerTask> getSchedulerTasks() {
        List<SchedulerTask> schedulerTasks = new ArrayList<SchedulerTask>();
        List<JobBinding> jobBindings = getAbstractBindings();
        for (JobBinding jobBinding : jobBindings) {
            SchedulerTask schedulerTask = jobBinding.buildSchedulerTask();
            if (schedulerTask == null) {
                break;
            }
            schedulerTasks.add(schedulerTask);
        }
        return schedulerTasks;
    }

    private static List<JobBinding> getAbstractBindings() {
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

    private static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
