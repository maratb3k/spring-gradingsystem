package com.example.gradingsystem.exceptions;

import org.springframework.http.HttpStatus;

public class ApiExceptionMessage {
    private String message;
    private HttpStatus httpStatus;

    public ApiExceptionMessage(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
