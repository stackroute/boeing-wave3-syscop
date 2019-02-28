package com.stackroute.AppRegistration.exceptions;

/**
 * Custom Exceptions thrown when we try to save already existing exception
 */
public class ApplicationAlreadyExistException extends Exception {

    private String message;

    public ApplicationAlreadyExistException(String message){
        super(message);
        this.message = message;
    }
}
