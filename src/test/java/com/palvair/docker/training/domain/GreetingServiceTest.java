package com.palvair.docker.training.domain;

import com.palvair.docker.training.domain.exception.FailedPersistenceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class GreetingServiceTest {

    @InjectMocks
    private GreetingService greetingService;
    @Mock
    private GreetingRepository greetingRepository;

    @Test
    void should_return_greeting_when_repository_save_successfully() {
        final Greeting greeting = greetingService.save(new Greeting(""));

        assertThat(greeting).isNotNull();
    }

    @Test
    void should_throw_domain_exception_when_saving_fail() {
        doThrow(new FailedPersistenceException(new IOException()))
                .when(greetingRepository).save(any());

        assertThatExceptionOfType(FailedPersistenceException.class)
                .isThrownBy(() -> greetingService.save(new Greeting("")))
                .withRootCauseInstanceOf(IOException.class);
    }
}