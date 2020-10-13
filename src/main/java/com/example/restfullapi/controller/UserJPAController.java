package com.example.restfullapi.controller;

import com.example.restfullapi.exception.UserNotFoundException;
import com.example.restfullapi.model.Post;
import com.example.restfullapi.model.User;
import com.example.restfullapi.repo.PostRepository;
import com.example.restfullapi.repo.UserRepository;
import com.example.restfullapi.util.Mappings;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(Mappings.JPA)
@Slf4j
public class UserJPAController {

    // == fields ==
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    // == request methods ==
    // http://localhost:8082/users
    @GetMapping(Mappings.USERS)
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userRepository.findAll();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // == request methods ==
    // http://localhost:8082/users
    @GetMapping(value = "ddd/header", headers = "version=1")
    public ResponseEntity<?> getAllUsersV2() {
        List<User> users = userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // http://localhost:8082/users/{id}
    @GetMapping(Mappings.USERS_ID)
    public ResponseEntity<?> getUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty())
            throw new UserNotFoundException("id=" + id);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name" + "");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("UserFilter", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(user);
        mapping.setFilters(filters);
        return new ResponseEntity<>(mapping, HttpStatus.OK);
    }


    @PostMapping(Mappings.USERS)
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    // http://localhost:8082/users/{id}
    @DeleteMapping(Mappings.USERS_ID)
    public void removeUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    // http://localhost:8082/users
    @GetMapping(Mappings.USERS_ID_POSTS)
    public ResponseEntity<?> getAllPosts(@PathVariable int id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty())
            throw new UserNotFoundException("id=" + id);

        return new ResponseEntity<>(userOptional.get().getPosts(), HttpStatus.OK);
    }

    @PostMapping(Mappings.USERS_ID_POSTS)
    public ResponseEntity<?> createPost(@PathVariable int id, @RequestBody Post post) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty())
            throw new UserNotFoundException("id=" + id);
        User user = userOptional.get();
        post.setUser(user);

        log.info("post: {}", post);
        log.info("user: {}", user);

        postRepository.save(post);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
