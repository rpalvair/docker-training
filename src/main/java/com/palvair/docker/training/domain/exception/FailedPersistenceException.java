package com.palvair.docker.training.domain.exception;

public class FailedPersistenceException extends DomainException {

    public FailedPersistenceException(final Exception exception) {
        super(exception);
    }
}
