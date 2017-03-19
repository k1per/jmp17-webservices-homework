package com.epam.learning.aykorenev.webservices.exceptions;

/**
 * Created by Anton_Korenev on 3/18/2017.
 */
public class UserDoesNotExistsException extends RuntimeException {
    public UserDoesNotExistsException(String message) {
        super(message);
    }
}
