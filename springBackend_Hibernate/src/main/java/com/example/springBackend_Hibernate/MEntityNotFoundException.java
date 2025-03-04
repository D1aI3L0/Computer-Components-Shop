package com.example.springBackend_Hibernate;

public class MEntityNotFoundException extends Exception{
    public MEntityNotFoundException(String clientNotFound) {
        super(clientNotFound);
    }
}
