package com.example.gradingsystem.exceptions;

public class RobucksExceededException extends RuntimeException {
    public RobucksExceededException(String message) {
        super(message);
    }
}
