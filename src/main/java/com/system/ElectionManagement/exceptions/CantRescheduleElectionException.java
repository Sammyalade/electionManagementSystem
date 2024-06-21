package com.system.ElectionManagement.exceptions;

public class CantRescheduleElectionException extends RuntimeException {
    public CantRescheduleElectionException(String message) {
        super(message);
    }
}
