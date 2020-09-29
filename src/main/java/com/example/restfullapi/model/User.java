package com.example.restfullapi.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class User {

    // == constructors ==
    public User(Integer id, String name, Date brithday) {
        this.id = id;
        this.name = name;
        this.brithday = brithday;
    }

    public User() {
    }

    // == fields ==
    private Integer id;
    private String name;
    private Date brithday;
}
