package com.satt294.passwdbuddy.services;

import com.satt294.passwdbuddy.exceptions.PBAuthenticationException;

/**
 * Service to authenticate the PIN entered by the user.
 * Created by satt2 on 7/9/2017.
 */

public class Authenticator {

    /**
     * Singleton instance
     */
    private static final Authenticator instance = new Authenticator();

    public boolean authenticate(String pin) throws PBAuthenticationException {
        // TODO: P1: Authenticate the pin against the one in DB
        Integer iPin = Integer.valueOf(pin);
        if (iPin > 5000) {
            return true;
        }
        throw new PBAuthenticationException("The PIN is invalid.");
    }

    /**
     * Get the instance of the authenticator service
     */
    public static Authenticator getInstance() {
        return instance;
    }
}
