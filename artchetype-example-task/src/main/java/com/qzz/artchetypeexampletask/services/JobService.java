package com.qzz.artchetypeexampletask.services;

import org.quartz.SchedulerException;

public interface JobService {
    void addJob(String jobClassName, String jobGroupName, String cronExpression) throws Exception;

    void pauseJob(String jobClassName, String jobGroupName);

    void restartJob(String jobClassName, String jobGroupName);

    void updateJob(String jobClassName, String jobGroupName, String cronExpression) throws SchedulerException;

    void deleteJob(String jobClassName, String jobGroupName);
}
