package com.revature.exceptions;

/**
 * Runtime Exception to be thrown when authentication fails/
 */
public class AuthenticationException extends RuntimeException{

    /**
     * No args constructor that throws a default message
     */
    public AuthenticationException() {
        super("Authentication failed!");
    }

    /**
     * Takes in a custom message to be thrown
     * @param message message to be thrown
     */
    public AuthenticationException(String message) {
        super("message");
    }
}
