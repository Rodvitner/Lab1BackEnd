package com.exception;

/**
 * Created by archer on 2016-11-21.
 */
public class UserException extends Exception{
    public UserException(String message) {
        super(message);
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserException(Throwable cause) {
        super(cause);
    }

    public UserException() {
    }
}
