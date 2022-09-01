package com.prasanna.rest.webservices.restfulwebservices.controller;

import com.prasanna.rest.webservices.restfulwebservices.model.Name;
import com.prasanna.rest.webservices.restfulwebservices.model.Person;
import com.prasanna.rest.webservices.restfulwebservices.model.Person2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

    @GetMapping("/v1/persons")
    public Person getFirstVersionOfPerson() {
        return new Person("Prasanna Kumar");
    }

    @GetMapping("/v2/persons")
    public Person2 getSecondVersionOfPerson() {
        return new Person2(new Name("Prasanna", "Kumar"));
    }

    @GetMapping(path = "/persons", params = "1")
    public Person getFirstVersionOfPersonReqParam() {
        return new Person("Prasanna Kumar");
    }

    @GetMapping(path = "/persons/header", headers = "1")
    public Person getFirstVersionOfPersonReqHeader() {
        return new Person("Prasanna Kumar");
    }
}
