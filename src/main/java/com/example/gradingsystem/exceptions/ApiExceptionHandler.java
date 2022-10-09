package com.example.gradingsystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {TeacherNotFoundException.class})
    public ResponseEntity<Object> handleApiRequestException(TeacherNotFoundException e) {
        ApiExceptionMessage apiExceptionMessage = new ApiExceptionMessage(
                e.getMessage(),
                HttpStatus.BAD_REQUEST
        );
        return new ResponseEntity<>(apiExceptionMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {StudentNotFoundException.class})
    public ResponseEntity<Object> handleApiRequestException(StudentNotFoundException e) {
        ApiExceptionMessage apiExceptionMessage = new ApiExceptionMessage(
                e.getMessage(),
                HttpStatus.BAD_REQUEST
        );
        return new ResponseEntity<>(apiExceptionMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {GroupNotFoundException.class})
    public ResponseEntity<Object> handleApiRequestException(GroupNotFoundException e) {
        ApiExceptionMessage apiExceptionMessage = new ApiExceptionMessage(
                e.getMessage(),
                HttpStatus.BAD_REQUEST
        );
        return new ResponseEntity<>(apiExceptionMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {RobucksExceededException.class})
    public ResponseEntity<Object> handleApiRequestException(RobucksExceededException e) {
        ApiExceptionMessage apiExceptionMessage = new ApiExceptionMessage(
                e.getMessage(),
                HttpStatus.BAD_REQUEST
        );
        return new ResponseEntity<>(apiExceptionMessage, HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler(value = {NullRobucksException.class})
    public ResponseEntity<Object> handleApiRequestException(NullRobucksException e) {
        ApiExceptionMessage apiExceptionMessage = new ApiExceptionMessage(
                e.getMessage(),
                HttpStatus.BAD_REQUEST
        );
        return new ResponseEntity<>(apiExceptionMessage, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = {SubjectNotFoundException.class})
    public ResponseEntity<Object> handleApiRequestException(SubjectNotFoundException e) {
        ApiExceptionMessage apiExceptionMessage = new ApiExceptionMessage(
                e.getMessage(),
                HttpStatus.BAD_REQUEST
        );
        return new ResponseEntity<>(apiExceptionMessage, HttpStatus.NOT_ACCEPTABLE);
    }
}
