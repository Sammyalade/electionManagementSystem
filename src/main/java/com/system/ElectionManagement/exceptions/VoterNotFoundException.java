package com.system.ElectionManagement.exceptions;

public class VoterNotFoundException extends RuntimeException{
    public VoterNotFoundException(String message) {
        super(message);
    }
}
