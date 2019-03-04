package com.stackroute.exception;

public class UserAlreadyExistsException extends Exception{
    final String message;
    public UserAlreadyExistsException(String message){
        super(message);
        this.message = message;
    }
}
