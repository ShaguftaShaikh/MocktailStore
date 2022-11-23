package com.api.mocktailstore.util;

public enum ResponseStatus {

    SUCCESS("Success"),
    FAILED("failed");

    private String status;

    private ResponseStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }
}
