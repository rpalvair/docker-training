package com.palvair.docker.training.domain.exception;

public class DomainException extends RuntimeException {

    public DomainException(final Exception exception) {
        super(exception);
    }
}
