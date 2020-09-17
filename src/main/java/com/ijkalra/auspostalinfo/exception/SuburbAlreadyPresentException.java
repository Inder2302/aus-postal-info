package com.ijkalra.auspostalinfo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SuburbAlreadyPresentException extends RuntimeException{
    private static final long serialVersionUID = -1319916864835233181L;

    public SuburbAlreadyPresentException(String message) {
        super(message);
    }
}
