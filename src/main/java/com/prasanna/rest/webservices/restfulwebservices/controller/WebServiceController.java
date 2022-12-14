package com.prasanna.rest.webservices.restfulwebservices.controller;

import com.prasanna.rest.webservices.restfulwebservices.dto.UserDao;
import com.prasanna.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.prasanna.rest.webservices.restfulwebservices.model.UserDetails;
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
    public List<UserDetails> retrieveAllUsers() {
        return userDao.findAll();
    }

    @GetMapping("/users/{id}")
    public UserDetails retrieveSingleUsers(@PathVariable int id) {
        UserDetails userDetails = userDao.findSingleUser(id);
        if (userDetails == null)
            throw new UserNotFoundException("id: " + id);
        return userDetails;
    }

    @GetMapping("/users/entity/{id}")
    public EntityModel<UserDetails> retrieveEntityUsers(@PathVariable int id) {
        UserDetails userDetails = userDao.findSingleUser(id);
        if (userDetails == null)
            throw new UserNotFoundException("id: " + id);

        /*
         * Below we are fetching the link for the method retrieveAllUsers().
         */
        WebMvcLinkBuilder linkBuilder =
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                        .methodOn(this.getClass()).retrieveAllUsers());

        return EntityModel.of(userDetails).add(linkBuilder.withRel("all-users"));
    }

    @DeleteMapping("/users/{id}")
    public void deleteUsers(@PathVariable int id) {
        userDao.deleteUser(id);
    }

    @PostMapping("/users")
    public ResponseEntity<UserDetails> createUser(@Valid @RequestBody UserDetails userDetails) {
        UserDetails savedUserDetails = userDao.createUser(userDetails);
        URI location =
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                        .buildAndExpand(savedUserDetails.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
