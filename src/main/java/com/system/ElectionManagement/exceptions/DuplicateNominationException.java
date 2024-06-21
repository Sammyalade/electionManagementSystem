package com.system.ElectionManagement.exceptions;

public class DuplicateNominationException extends RuntimeException{
    public DuplicateNominationException(String message) {
        super(message);
    }
}
