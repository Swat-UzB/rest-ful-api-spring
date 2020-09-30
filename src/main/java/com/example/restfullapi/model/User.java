package com.example.restfullapi.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
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
    @Positive
    private Integer id;
    @Size(min = 2)
    private String name;
    @Past
    private Date brithday;
}
