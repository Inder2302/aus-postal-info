package com.ijkalra.auspostalinfo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -241478501821271385L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
