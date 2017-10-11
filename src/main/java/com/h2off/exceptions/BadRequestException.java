package com.h2off.exceptions;

/**
 *
 * @author phil
 */
public class BadRequestException extends RuntimeException {

    public BadRequestException() {
        super();
    }
    
    public BadRequestException(String message) {
        super(message);
    }
    
    
    
}
