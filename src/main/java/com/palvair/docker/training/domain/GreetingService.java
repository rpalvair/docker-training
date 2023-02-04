package com.palvair.docker.training.domain;

import com.palvair.docker.training.domain.exception.FailedPersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GreetingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingService.class);

    private final GreetingRepository greetingRepository;

    public GreetingService(final GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    public Greeting save(final Greeting greeting) {
        try {
            greetingRepository.save(greeting);
            return greeting;
        } catch (final FailedPersistenceException exception) {
            LOGGER.error("Error while persisting greeting with content {}, error: {}", greeting.content(), exception.getMessage());
            throw exception;
        }
    }
}
