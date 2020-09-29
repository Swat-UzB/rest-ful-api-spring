package com.example.restfullapi.controller;

import com.example.restfullapi.model.User;
import com.example.restfullapi.service.UserDaoService;
import com.example.restfullapi.util.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    // == fields ==
    @Autowired
    private UserDaoService userDaoService;

    // == request methods ==
    // http://localhost:8082/users
    @GetMapping(Mappings.USERS)
    public List<User> getAllUsers() {
        return userDaoService.getAll();
    }

    // http://localhost:8082/users/{id}
    @GetMapping(Mappings.USERS_ID)
    public User getUser(@PathVariable int id) {
        return userDaoService.getUser(id);
    }

    @PostMapping(Mappings.USERS)
    public void createUser(@RequestBody User user){
     userDaoService.add(user);

    }
}
