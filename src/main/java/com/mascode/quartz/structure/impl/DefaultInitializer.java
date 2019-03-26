package com.mascode.quartz.structure.impl;

import com.mascode.quartz.structure.JobBinding;
import com.mascode.quartz.structure.QuartzInitializer;
import com.mascode.quartz.structure.annotation.SchedulerBinding;
import com.mascode.quartz.structure.po.SchedulerTask;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mazijun@58.com
 */
@Service
public class DefaultInitializer implements QuartzInitializer, ApplicationContextAware {

    private boolean isInit = false;
    private ApplicationContext applicationContext;

    @PostConstruct
    private void init() {
        try {
            initQuartz();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void initQuartz() throws SchedulerException {
        if (isInit) {
            return;
        }
        initInner();
    }

    private synchronized void initInner() throws SchedulerException {
        if (isInit) {
            return;
        }
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        List<SchedulerTask> schedulerTasks = getSchedulerTasks();
        if (!CollectionUtils.isEmpty(schedulerTasks)) {
            for (SchedulerTask schedulerTask : schedulerTasks) {
                initOneJob(scheduler, schedulerTask);
            }
        }
        scheduler.start();
        isInit = true;
    }


    private void initOneJob(Scheduler scheduler, SchedulerTask schedulerTask) throws SchedulerException {
        if (schedulerTask == null) {
            log("schedulerTask is null");
        }
        JobDetail jobDetail = schedulerTask.getJobDetail();
        Trigger trigger = schedulerTask.getTrigger();
        scheduler.addJob(jobDetail, true);
        TriggerKey triggerKey = trigger.getKey();
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
                log("init : " + jobBinding.getClass().getName() + " , schedulerTask is null.");
                break;
            }
            schedulerTasks.add(schedulerTask);
        }
        return schedulerTasks;
    }

    private List<JobBinding> getJobBindings() {
        List<JobBinding> list = new ArrayList();
        String[] beans = getApplicationContext().getBeanNamesForAnnotation(SchedulerBinding.class);
        for (String beanName : beans) {
            Class<?> beanType = getApplicationContext()
                    .getType(beanName);
            SchedulerBinding schedulerBinding = beanType.getAnnotation(SchedulerBinding.class);
            if (schedulerBinding != null) {
                Object obj = getApplicationContext().getBean(beanName);
                if (obj instanceof JobBinding) {
                    JobBinding jobBinding = (JobBinding) obj;
                    list.add(jobBinding);
                    log("init : " + jobBinding.getClass().getName() + " , desc : " + schedulerBinding.des());
                }
            }
        }
        return list;
    }

    private ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
