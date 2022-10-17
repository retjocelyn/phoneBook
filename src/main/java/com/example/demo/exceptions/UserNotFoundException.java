package com.example.demo.exceptions;

public class UserNotFoundException  extends RuntimeException{

    public UserNotFoundException(long id) {
        super("User with id " + id + " does not exist");
    }

}
