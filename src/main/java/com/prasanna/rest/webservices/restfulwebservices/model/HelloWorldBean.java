package com.prasanna.rest.webservices.restfulwebservices.model;

import lombok.Data;

@Data
public class HelloWorldBean {
    private String message;

    public HelloWorldBean(String message) {
        this.message = message;
    }
}
