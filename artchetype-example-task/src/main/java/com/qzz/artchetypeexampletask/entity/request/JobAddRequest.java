package com.qzz.artchetypeexampletask.entity.request;

import lombok.Data;

@Data
public class JobAddRequest {
    private String jobClassName;

    private String jobGroupName;

    private String cronExpression;

}
