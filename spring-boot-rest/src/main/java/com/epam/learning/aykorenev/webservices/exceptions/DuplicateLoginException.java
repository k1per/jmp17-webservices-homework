package com.epam.learning.aykorenev.webservices.exceptions;

/**
 * Created by Anton_Korenev on 3/18/2017.
 */
public class DuplicateLoginException extends RuntimeException {
    public DuplicateLoginException(String s) {
        super(s);
    }
}
