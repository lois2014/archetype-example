package com.qzz.artchetypeexamplerabbitmq.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class MessageDTO<T> implements Serializable {

    private static final long serialVersionUID = 10005678L;

    private T body;

    private String requestId;

}
