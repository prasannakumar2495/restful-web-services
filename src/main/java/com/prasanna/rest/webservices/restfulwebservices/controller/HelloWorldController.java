package com.prasanna.rest.webservices.restfulwebservices.controller;

import com.prasanna.rest.webservices.restfulwebservices.dto.UserDao;
import com.prasanna.rest.webservices.restfulwebservices.model.HelloWorldBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/getmethod")
    public String helloWorld() {
        return "Hello World!";
    }

    @GetMapping("/getmethodbean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World!");
    }

    @GetMapping("/getmethodbeanpathvariable/{name}")
    public HelloWorldBean helloWorldBeanPath(@PathVariable String name) {
        return new HelloWorldBean("Hello World!" + name);
    }
}
