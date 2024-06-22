package com.electionManagementSystem.exceptions;

public enum ExceptionMessages {
    INCORRECT_ID("object with id not found");
    String message;
    ExceptionMessages(String message){
        this.message= message;
    }
    public String getMessage(){
        return this.message;
    }
}
