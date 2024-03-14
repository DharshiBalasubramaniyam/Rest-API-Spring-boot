package com.example.demo.exceptions;

public class UserServiceLogicException extends Exception{
    public UserServiceLogicException() {
        super("Something went wrong. Please try again later!");
    }
}