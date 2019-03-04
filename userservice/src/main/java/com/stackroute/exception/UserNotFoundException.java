package com.stackroute.exception;

public class UserNotFoundException extends Exception{

    final String message;
    public UserNotFoundException(String message){
        super(message);
        this.message = message;
    }
}
