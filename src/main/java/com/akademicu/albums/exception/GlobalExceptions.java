package com.akademicu.albums.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptions {

    @ExceptionHandler(AlbumNotFoundExceptionClass.class)
    public ResponseEntity<Object> productNotFoundEx(AlbumNotFoundExceptionClass exc){

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exc.getMessage());
    }
}
