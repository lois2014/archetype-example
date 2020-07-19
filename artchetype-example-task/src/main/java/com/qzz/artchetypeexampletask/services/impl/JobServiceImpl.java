package com.qzz.artchetypeexampletask.services.impl;

import com.qzz.artchetypeexampletask.scheduler.BaseJob;
import com.qzz.artchetypeexampletask.services.JobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Slf4j
public class JobServiceImpl implements JobService {

    private final Scheduler scheduler;

    @Override
    public void addJob(String jobClassName, String jobGroupName, String cronExpression) throws Exception {
        scheduler.start();
        JobDetail jobDetail = JobBuilder.newJob(getClass(jobClassName).getClass())
                .withIdentity(jobClassName, jobGroupName)
                .build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withIdentity(jobClassName, jobGroupName)
                .withSchedule(scheduleBuilder).build();
        try {
            scheduler.scheduleJob(jobDetail, cronTrigger);
        } catch (Exception e) {
            log.error("Create Job error {}", e.getMessage());
            throw new RuntimeException("创建定时任务失败");
        }
    }

    @Override
    public void pauseJob(String jobClassName, String jobGroupName) {
        try {
            scheduler.pauseJob(JobKey.jobKey(jobClassName, jobGroupName));
        } catch (SchedulerException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void restartJob(String jobClassName, String jobGroupName) {
        try {
            scheduler.resumeJob(JobKey.jobKey(jobClassName, jobGroupName));
        } catch (SchedulerException e) {
         log.error("resume error {}", e.getMessage());
         throw new RuntimeException("resume 错误");
        }
    }

    @Override
    public void updateJob(String jobClassName, String jobGroupName, String cronExpression) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobClassName, jobGroupName);
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
            CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            cronTrigger = cronTrigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            scheduler.rescheduleJob(triggerKey, cronTrigger);
        } catch (SchedulerException ex) {
            log.error("update error {}", ex.getMessage());
            throw new RuntimeException("更新定时任务失败");
        }
    }

    @Override
    public void deleteJob(String jobClassName, String jobGroupName) {
        try {
            scheduler.deleteJob(JobKey.jobKey(jobClassName, jobGroupName));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    protected static BaseJob getClass(String jobClassName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class clazz = Class.forName(jobClassName);
        return (BaseJob) clazz.newInstance();
    }
}
