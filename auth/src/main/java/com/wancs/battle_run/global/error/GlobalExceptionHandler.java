package com.wancs.battle_run.global.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> defaultException(Exception e) {
        //log.info(ex.getClass().getName());
        //log.error("error", ex);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
