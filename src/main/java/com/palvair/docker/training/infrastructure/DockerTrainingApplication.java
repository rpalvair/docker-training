package com.palvair.docker.training.infrastructure;

import com.palvair.docker.training.domain.GreetingRepository;
import com.palvair.docker.training.domain.GreetingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.palvair.docker.training.application", "com.palvair.docker.training.infrastructure.persistence"})
public class DockerTrainingApplication {

    public static void main(String[] args) {
        SpringApplication.run(DockerTrainingApplication.class, args);
    }


    @Bean
    public GreetingService greetingService(final GreetingRepository greetingRepository) {
        return new GreetingService(greetingRepository);
    }
}
