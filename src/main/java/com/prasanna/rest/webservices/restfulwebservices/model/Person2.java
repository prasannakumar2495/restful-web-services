package com.prasanna.rest.webservices.restfulwebservices.model;

import lombok.Data;

@Data
public class Person2 {
    private Name name;

    public Person2(Name name) {
        this.name = name;
    }
}
