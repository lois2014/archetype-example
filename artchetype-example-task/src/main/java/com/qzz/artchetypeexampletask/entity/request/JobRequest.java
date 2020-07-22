package com.qzz.artchetypeexampletask.entity.request;

import lombok.Data;

@Data
public class JobRequest {
    private String jobClassName;

    private String jobGroupName;

    private String cronExpression;

}
