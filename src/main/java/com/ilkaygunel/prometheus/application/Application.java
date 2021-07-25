package com.ilkaygunel.prometheus.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ilkay.gunel
 */

@SpringBootApplication
@ComponentScan(basePackages = {"com.ilkaygunel.*"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
