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

    }

    @Override
    public void restartJob(String jobClassName, String jobGroupName) {

    }

    @Override
    public void updateJob(String jobClassName, String jobGroupName, String cronExpression) {

    }

    @Override
    public void deleteJob(String jobClassName, String jobGroupName) {

    }

    protected static BaseJob getClass(String jobClassName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class clazz = Class.forName(jobClassName);
        return (BaseJob) clazz.newInstance();
    }
}
