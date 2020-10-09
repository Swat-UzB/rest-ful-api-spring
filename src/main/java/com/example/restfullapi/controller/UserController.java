package com.example.restfullapi.controller;

import com.example.restfullapi.exception.UserNotFoundException;
import com.example.restfullapi.model.User;
import com.example.restfullapi.service.UserDaoService;
import com.example.restfullapi.util.Mappings;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    // == fields ==
    @Autowired
    private UserDaoService userDaoService;

    // == request methods ==
    // http://localhost:8082/users
    @GetMapping(Mappings.USERS)
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userDaoService.getAll();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // == request methods ==
    // http://localhost:8082/users
    @GetMapping(value = "ddd/header", headers = "version=1")
    public ResponseEntity<?> getAllUsersV2() {
        List<User> users = userDaoService.getAll();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // http://localhost:8082/users/{id}
    @GetMapping(Mappings.USERS_ID)
    public ResponseEntity<?> getUser(@PathVariable int id) {
        User user = userDaoService.getUser(id);
        if (user == null)
            throw new UserNotFoundException("id=" + id);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name" +
                        "");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("UserFilter", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(user);
        mapping.setFilters(filters);
        return new ResponseEntity<>(mapping, HttpStatus.OK);
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
