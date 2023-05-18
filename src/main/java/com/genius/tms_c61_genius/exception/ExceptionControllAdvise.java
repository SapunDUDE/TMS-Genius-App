package com.genius.tms_c61_genius.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@ResponseBody
public class ExceptionControllAdvise {
    @ExceptionHandler(BadDataException.class)
    public ResponseEntity<ExceptionDetails> badDataExceptionHandler(BadDataException exception) {
        ExceptionDetails details = new ExceptionDetails(HttpStatus.CONFLICT, LocalDateTime.now(), exception.getMessage());
        return new ResponseEntity<>(details, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDetails> notFoundexceptionHandler(NotFoundException exception) {
        ExceptionDetails details = new ExceptionDetails(HttpStatus.NOT_FOUND, LocalDateTime.now(), exception.getMessage());
        return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDetails> exceptionHandler() {
        ExceptionDetails details = new ExceptionDetails(
                HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now(), "internal server error"
        );
        return new ResponseEntity<>(details, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ExceptionDetails> ValidationExceptionHandler() {
        ExceptionDetails details = new ExceptionDetails(HttpStatus.CONFLICT, LocalDateTime.now(), "input is not valid");
        return new ResponseEntity<>(details, HttpStatus.CONFLICT);
    }
}
