package com.exam.helper;

public class UserFoundException extends Exception {
    public UserFoundException() {
        super("User with this username is present in DB, Try with another one.");
    }
    public UserFoundException(String msg) {
        super(msg);
    }
}
