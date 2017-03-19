package com.epam.learning.aykorenev.webservices.exceptions;

/**
 * Created by Anton_Korenev on 3/18/2017.
 */
public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException(String s) {
        super(s);
    }
}
