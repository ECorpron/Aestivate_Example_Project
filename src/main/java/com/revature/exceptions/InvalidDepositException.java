package com.revature.exceptions;

/**
 * Runtime Exception to be thrown when an invalid Deposit is attempted
 */
public class InvalidDepositException extends RuntimeException{

    /**
     * No args constructor that throws a default message
     */
    public InvalidDepositException() {
        super("Invalid Deposit Amount!");
    }

    /**
     * Takes in a custom message to be thrown
     * @param message message to be thrown
     */
    public InvalidDepositException(String message) {
        super("message");
    }
}
