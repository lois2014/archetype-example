package com.qzz.artchetypeexampletask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration;

@EnableAutoConfiguration(exclude = {QuartzAutoConfiguration.class})
@SpringBootApplication
public class ArtchetypeExampleTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArtchetypeExampleTaskApplication.class, args);
    }

}
