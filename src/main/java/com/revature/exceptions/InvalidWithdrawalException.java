package com.revature.exceptions;

/**
 * Runtime exception to be thrown when an InvalidWithdrawal is attempted
 */
public class InvalidWithdrawalException extends RuntimeException{

    /**
     * No args constructor that throws a default message
     */
    public InvalidWithdrawalException() {
        super("Invalid Withdrawal Amount!");
    }

    /**
     * Takes in a custom message to be thrown
     * @param message message to be thrown
     */
    public InvalidWithdrawalException(String message) {
        super("message");
    }
}
