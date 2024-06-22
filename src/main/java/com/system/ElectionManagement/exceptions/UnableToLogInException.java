package com.system.ElectionManagement.exceptions;

import com.system.ElectionManagement.ElectionManagementApplication;

public class UnableToLogInException extends RuntimeException {
    public UnableToLogInException(String message) {
        super(message);
    }
}
