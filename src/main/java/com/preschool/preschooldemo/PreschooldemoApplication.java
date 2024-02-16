package com.preschool.preschooldemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class PreschooldemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PreschooldemoApplication.class, args);
    }

}
