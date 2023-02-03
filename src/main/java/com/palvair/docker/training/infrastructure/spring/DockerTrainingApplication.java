package com.palvair.docker.training.infrastructure.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.palvair.docker.training.application"})
public class DockerTrainingApplication {

    public static void main(String[] args) {
        SpringApplication.run(DockerTrainingApplication.class, args);
    }
}
