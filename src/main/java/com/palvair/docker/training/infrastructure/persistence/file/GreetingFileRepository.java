package com.palvair.docker.training.infrastructure.persistence.file;

import com.palvair.docker.training.domain.Greeting;
import com.palvair.docker.training.domain.GreetingRepository;
import com.palvair.docker.training.domain.exception.FailedPersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Repository
public class GreetingFileRepository implements GreetingRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingFileRepository.class);

    private static final String GREETING_TXT = "greeting.txt";
    private final String directory;

    public GreetingFileRepository(@Value("${output.directory}") String directory) {
        this.directory = directory;
    }

    @Override
    public void save(Greeting greeting) {
        final String fileName = directory + "/" + GREETING_TXT;
        LOGGER.info("Writing content {} in file {}", greeting.content(), fileName);
        try (final FileWriter fileWriter = new FileWriter(fileName, true);
             final BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(greeting.content());
            bufferedWriter.write("\n");
            LOGGER.info("Content successfully written in file {}", fileName);
        } catch(final IOException exception) {
            throw new FailedPersistenceException(exception);
        }
    }
}
