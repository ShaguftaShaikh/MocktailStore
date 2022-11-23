package com.api.mocktailstore.exceptions;

public class UserAlreadyExists extends RuntimeException {

    private final String email;

    public UserAlreadyExists(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

}
