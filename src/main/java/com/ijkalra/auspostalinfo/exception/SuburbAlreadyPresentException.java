package com.ijkalra.auspostalinfo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SuburbAlreadyPresentException extends RuntimeException{
    public SuburbAlreadyPresentException(String message) {
        super(message);
    }
}
