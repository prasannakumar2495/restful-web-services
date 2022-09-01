package com.prasanna.rest.webservices.restfulwebservices.model;

import lombok.Data;

@Data
public class Name {
    private String firstName;
    private String lastName;

    public Name(String prasanna, String kumar) {
        this.firstName=prasanna;
        this.lastName=kumar;
    }
}
