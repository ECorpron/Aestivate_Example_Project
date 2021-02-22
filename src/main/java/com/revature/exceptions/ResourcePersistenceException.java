package com.revature.exceptions;

/**
 * Runtime Exception that is thrown when there is an error saving data to the database.
 */
public class ResourcePersistenceException extends RuntimeException {

    /**
     * Takes a custom message to be thrown when the exception is thrown
     * @param message message to be thrown by the exception
     */
    public ResourcePersistenceException(String message) {
        super(message);
    }
}
