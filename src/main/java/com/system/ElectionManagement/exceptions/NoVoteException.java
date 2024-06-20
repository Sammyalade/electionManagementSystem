package com.system.ElectionManagement.exceptions;

public class NoVoteException extends RuntimeException{
    public NoVoteException(String message) {
        super(message);
    }
}
