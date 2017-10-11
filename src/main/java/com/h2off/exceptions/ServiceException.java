package com.h2off.exceptions;

/**
 *
 * @author phil
 */
public class ServiceException extends RuntimeException {

    private ErrorCodes code;
    
    public ServiceException(String message) {
        this(null, message);
    }
    
    public ServiceException(ErrorCodes code, String message) {
        this(code, message, null);
    }
    
    public ServiceException(String message, Exception cause) {
        this(null, message, cause);
    }
    
    public ServiceException(ErrorCodes code, String message, Exception cause) {
        super(message, cause);
        this.code = code;
    }

    public ErrorCodes getCode() {
        return code;
    }

}
