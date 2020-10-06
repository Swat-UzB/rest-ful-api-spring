package com.example.restfullapi.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloWorldBean {

    // == fields ==
    private String message;

    // == constructors ==
    public   HelloWorldBean(String message) {
        this.message = message;

    }
}
