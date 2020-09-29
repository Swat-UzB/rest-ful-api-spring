package com.example.restfullapi.exception;


import lombok.Getter;

import java.util.Date;

@Getter
public class ExceptionResponse {

    // == fields ==
    private Date timestamp;
    private String message;
    private String details;

    // == constructors ==
    public ExceptionResponse(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
}
