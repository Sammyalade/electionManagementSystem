package com.system.ElectionManagement.exceptions;

public class NominationNotFoundException extends RuntimeException{
    public NominationNotFoundException(String message){
        super(message);
    }
}
