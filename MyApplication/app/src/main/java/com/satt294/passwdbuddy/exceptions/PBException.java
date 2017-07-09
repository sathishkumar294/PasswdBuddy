package com.satt294.passwdbuddy.exceptions;

/**
 * Parent exception class for all exceptions
 * Created by satt2 on 7/9/2017.
 */

public abstract class PBException extends Exception {

    /**
     * Meaning full exception message
     */
    private String expMsg;

    public PBException() {
        super();
        expMsg = "Unknown or unhandled exception";
    }

    public PBException(String message) {
        super(message);
        expMsg = message;
    }

    public PBException(String message, Exception e) {
        super(message, e);
        expMsg = message;
    }

    public String getMessage() {
        return expMsg;
    }

}
