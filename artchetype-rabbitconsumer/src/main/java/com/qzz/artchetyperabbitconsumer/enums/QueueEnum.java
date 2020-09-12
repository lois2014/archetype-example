package com.qzz.artchetyperabbitconsumer.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum QueueEnum {

    DIRECT_QUEUE_A("default.direct", "default.direct.a", "default.direct.a"),
    DIRECT_QUEUE_B("default.direct", "direct.b", "direct.route.b"),
    DIRECT_QUEUE_MULTIB("default.direct", "direct.b", "direct.route.multib"),

    ;
    private final String exchange;

    private final String queue;

    private final String routeKey;

}
