package com.prasanna.rest.webservices.restfulwebservices.exception;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorDetails{
    private final LocalDateTime timeStamp;
    private final String message;
    private final String details;

    public ErrorDetails(LocalDateTime timeStamp, String message, String details) {
        this.timeStamp = timeStamp;
        this.message = message;
        this.details = details;
    }
}
