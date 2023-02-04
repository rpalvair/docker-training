package com.palvair.docker.training.infrastructure.persistence.file;

import com.palvair.docker.training.domain.Greeting;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;


class GreetingFileRepositoryIT {

    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingFileRepositoryIT.class);
    private static final String CONTENT = "Hello World!";
    private static final String DIRECTORY = "target";
    private static final String FILE_NAME = "greeting.txt";

    private final GreetingFileRepository greetingFileRepository = new GreetingFileRepository(DIRECTORY);

    @Test
    void should_write_content_to_file_successfully() throws IOException {
        assertThatNoException().isThrownBy(() -> greetingFileRepository.save(new Greeting(CONTENT)));

        final Path path = Paths.get(DIRECTORY + "/" + FILE_NAME);
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line = reader.readLine();
            LOGGER.info("line = {}", line);
            assertThat(line).isNotNull()
                    .isEqualTo(CONTENT);
        }

    }
}