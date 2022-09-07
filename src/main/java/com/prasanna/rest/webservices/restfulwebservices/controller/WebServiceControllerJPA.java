package com.prasanna.rest.webservices.restfulwebservices.controller;

import com.prasanna.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.prasanna.rest.webservices.restfulwebservices.model.Post;
import com.prasanna.rest.webservices.restfulwebservices.model.UserDetails;
import com.prasanna.rest.webservices.restfulwebservices.repository.PostRepo;
import com.prasanna.rest.webservices.restfulwebservices.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class WebServiceControllerJPA {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PostRepo postRepo;

    @GetMapping("/jpa/users")
    public List<UserDetails> retrieveAllUsers() {
        return userRepo.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public UserDetails retrieveSingleUsers(@PathVariable int id) {
        Optional<UserDetails> userDetails = userRepo.findById(id);
        if (userDetails.isEmpty())
            throw new UserNotFoundException("id: " + id);
        return userDetails.get();
    }

    @GetMapping("/jpa/users/entity/{id}")
    public EntityModel<UserDetails> retrieveEntityUsers(@PathVariable int id) {
        Optional<UserDetails> userDetails = userRepo.findById(id);
        if (userDetails.isEmpty())
            throw new UserNotFoundException("id: " + id);

        /*
         * Below we are fetching the link for the method retrieveAllUsers().
         */
        WebMvcLinkBuilder linkBuilder =
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                        .methodOn(this.getClass()).retrieveAllUsers());

        return EntityModel.of(userDetails.get()).add(linkBuilder.withRel("all-users"));
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUsers(@PathVariable int id) {
        userRepo.deleteById(id);
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<UserDetails> createUser(@Valid @RequestBody UserDetails userDetails) {
        UserDetails savedUserDetails = userRepo.save(userDetails);
        URI location =
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                        .buildAndExpand(savedUserDetails.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrievePostForAUser(@PathVariable int id) {
        Optional<UserDetails> userDetails = userRepo.findById(id);
        if (userDetails.isEmpty())
            throw new UserNotFoundException("id: " + id);

        return userDetails.get().getPostList();
    }

    @PostMapping("/jpa/users/{id}/posts")
    public Post createPostForUser(@PathVariable int id,
                                  @Valid @RequestBody Post post) {
        Optional<UserDetails> userDetails = userRepo.findById(id);
        if (userDetails.isEmpty())
            throw new UserNotFoundException("id: " + id);

        post.setUserDetails(userDetails.get());
        return postRepo.save(post);
    }
}
