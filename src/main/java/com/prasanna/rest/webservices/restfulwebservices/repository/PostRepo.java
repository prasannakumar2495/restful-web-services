package com.prasanna.rest.webservices.restfulwebservices.repository;

import com.prasanna.rest.webservices.restfulwebservices.model.Post;
import com.prasanna.rest.webservices.restfulwebservices.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post, Integer> {
}
