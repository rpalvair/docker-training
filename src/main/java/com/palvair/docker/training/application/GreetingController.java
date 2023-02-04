package com.palvair.docker.training.application;

import com.palvair.docker.training.domain.Greeting;
import com.palvair.docker.training.domain.GreetingService;
import com.palvair.docker.training.domain.exception.DomainException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String GREETING_FORMAT = "Hello %s!";

    private final GreetingService greetingService;

    public GreetingController(final GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/greeting")
    public ResponseEntity<?> greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        try {
            final Greeting save = greetingService.save(new Greeting(String.format(GREETING_FORMAT, name)));
            return ResponseEntity.ok(save);
        } catch (final DomainException exception) {
            return ResponseEntity.internalServerError()
                    .body(exception);
        }
    }
}
