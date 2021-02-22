package com.revature.exceptions;

/**
 * A Runtime Exception to be thrown when an Invalid Request is made.
 */
public class InvalidRequestException extends RuntimeException {

    /**
     * No args constructor with a default message to be thrown.
     */
    public InvalidRequestException() {
        super("Invalid request was made!");
    }

    /**
     * Constructor that takes a custom message to throw
     * @param message message to be thrown by the exception
     */
    public InvalidRequestException(String message) {
        super(message);
    }
}
