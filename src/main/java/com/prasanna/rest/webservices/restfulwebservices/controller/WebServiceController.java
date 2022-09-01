package com.prasanna.rest.webservices.restfulwebservices.controller;

import com.prasanna.rest.webservices.restfulwebservices.dto.UserDao;
import com.prasanna.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.prasanna.rest.webservices.restfulwebservices.model.HelloWorldBean;
import com.prasanna.rest.webservices.restfulwebservices.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class WebServiceController {
    @Autowired
    private UserDao userDao;

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userDao.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveSingleUsers(@PathVariable int id) {
        User user = userDao.findSingleUser(id);
        if (user == null)
            throw new UserNotFoundException("id: " + id);
        return user;
    }

    @GetMapping("/users/entity/{id}")
    public EntityModel<User> retrieveEntityUsers(@PathVariable int id) {
        User user = userDao.findSingleUser(id);
        if (user == null)
            throw new UserNotFoundException("id: " + id);

        /*
         * Below we are fetching the link for the method retrieveAllUsers().
         */
        WebMvcLinkBuilder linkBuilder =
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                        .methodOn(this.getClass()).retrieveAllUsers());

        return EntityModel.of(user).add(linkBuilder.withRel("all-users"));
    }

    @DeleteMapping("/users/{id}")
    public void deleteUsers(@PathVariable int id) {
        userDao.deleteUser(id);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = userDao.createUser(user);
        URI location =
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                        .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
