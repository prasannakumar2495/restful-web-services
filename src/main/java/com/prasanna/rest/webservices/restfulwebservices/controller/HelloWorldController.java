package com.prasanna.rest.webservices.restfulwebservices.controller;

import com.prasanna.rest.webservices.restfulwebservices.model.HelloWorldBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

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

    @GetMapping("/getmethod-international")
    public String helloWorldInternational() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message", null,
                "Default Message", locale);
        //return "Hello World!";
    }
}
