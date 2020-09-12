package com.qzz.artchetypeexamplerabbitmq;

import com.qzz.artchetypeexamplerabbitmq.publisher.DirectPublisher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest
class ArtchetypeExampleRabbitmqApplicationTests {

    @Autowired
    private DirectPublisher publisher;

    @Test
    void contextLoads() {
    }

    @Test
    public void testSendDirectMessage() {
        for (int i = 0; i < 2; i++) {
            publisher.sendA("send A" + i + "..." + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        }
    }

    @Test
    public void testSendDirectMessageB() {
       publisher.sendB("send B ..." + LocalDateTime.now());
       publisher.sendMultiB("send B 2" + LocalDateTime.now());
    }



}
