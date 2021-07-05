package com.marian.j16rest_demo.users.controller;
public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException() {
        super();
    }
    public PersonNotFoundException(String message) {
        super(message);
    }
}
