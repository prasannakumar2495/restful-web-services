package com.prasanna.rest.webservices.restfulwebservices.model;

import lombok.Data;

@Data
public class Person {
    public Person(String name) {
        this.name = name;
    }

    private String name;
}
