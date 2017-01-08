package com.exception;

/**
 * Created by simonlundstrom on 22/11/16.
 */
public class PostException extends Exception{
    public PostException() {
    }

    public PostException(String message) {
        super(message);
    }

    public PostException(String message, Throwable cause) {
        super(message, cause);
    }

    public PostException(Throwable cause) {
        super(cause);
    }
}
