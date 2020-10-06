package com.example.restfullapi.controller;

import com.example.restfullapi.exception.UserNotFoundException;
import com.example.restfullapi.model.User;
import com.example.restfullapi.service.UserDaoService;
import com.example.restfullapi.util.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class UserController {

    // == fields ==
    @Autowired
    private UserDaoService userDaoService;

    // == request methods ==
    // http://localhost:8082/users
    @GetMapping(Mappings.USERS)
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(userDaoService.getAll(), HttpStatus.OK);
    }

    // http://localhost:8082/users/{id}
    @GetMapping(Mappings.USERS_ID)
    public ResponseEntity<?> getUser(@PathVariable int id) {
        User user = userDaoService.getUser(id);
        if (user == null)
            throw new UserNotFoundException("id=" + id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(Mappings.USERS)
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
        User savedUser = userDaoService.add(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    // http://localhost:8082/users/{id}
    @DeleteMapping(Mappings.USERS_ID)
    public void removeUser(@PathVariable int id) {
        User user = userDaoService.deleteUser(id);
        if (user == null)
            throw new UserNotFoundException("id=" + id);
    }
}
