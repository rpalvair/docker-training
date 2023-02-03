package com.palvair.docker.training.domain;

public record Greeting(String content) {

    private static final String GREETING_FORMAT = "Hello %s!";

    @Override
    public String content() {
        return String.format(GREETING_FORMAT, content);
    }

}
