package com.example.restfullapi.service;

import com.example.restfullapi.model.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class UserDaoService {

    // == fields ==
    private static List<User> users = new ArrayList();
    private static Integer userCount = 3;

    // == init ==
    @PostConstruct
    public void init() {
        users.add(new User(1, "Ulugbek", new Date()));
        users.add(new User(2, "Dilmurod", new Date()));
        users.add(new User(3, "Ismoil aka", new Date()));
    }

    // == public methods ==
    public List<User> getAll() {
        return users;
    }

    public User add(User user) {
        if (user.getId() == null) {
            user.setId(++userCount);
        }
        users.add(user);
        return user;
    }

    public User getUser(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public Boolean deleteUser(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                users.remove(user);
                return true;
            }
        }
        return false;
    }
}
