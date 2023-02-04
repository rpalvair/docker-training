package com.palvair.docker.training.application;

import com.palvair.docker.training.domain.Greeting;
import com.palvair.docker.training.domain.GreetingService;
import com.palvair.docker.training.domain.exception.FailedPersistenceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ContextConfiguration(classes = {GreetingController.class})
class GreetingControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private GreetingService greetingService;

    @Test
    void should_return_greeting_message() throws Exception {
        when(greetingService.save(any())).thenReturn(new Greeting("Hello Bob!"));

        this.mockMvc.perform(get("/greeting?name=Bob"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"content\":\"Hello Bob!\"}"));
    }

    @Test
    void should_return_error_when_exception_occurred_when_saving() throws Exception {
        doThrow(new FailedPersistenceException(new IOException("Sorry")))
                .when(greetingService).save(any());

        this.mockMvc.perform(get("/greeting?name=Bob"))
                .andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(content().json("{\"message\":\"java.io.IOException: Sorry\"}"));
    }
}