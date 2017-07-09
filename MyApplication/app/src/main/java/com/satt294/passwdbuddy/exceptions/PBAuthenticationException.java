package com.satt294.passwdbuddy.exceptions;

/**
 * Created by satt2 on 7/9/2017.
 */

public class PBAuthenticationException extends PBException {

    public PBAuthenticationException(Integer pin) {
        super("Entered pin " + pin + " is invalid.");
    }

    public PBAuthenticationException(Exception e) {
        super("Some exception while trying to authenticate", e);
    }

    public PBAuthenticationException(String message) {
        super(message);
    }

    public PBAuthenticationException(String message, Exception e) {
        super(message, e);
    }
}
