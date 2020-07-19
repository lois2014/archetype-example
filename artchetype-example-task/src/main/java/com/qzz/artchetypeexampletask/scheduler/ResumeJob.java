package com.qzz.artchetypeexampletask.scheduler;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@DisallowConcurrentExecution
public class ResumeJob implements BaseJob {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
//        throw new RuntimeException("错误");
        System.out.println("resume job start ...");
    }
}
