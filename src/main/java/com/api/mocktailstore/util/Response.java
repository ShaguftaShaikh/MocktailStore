package com.api.mocktailstore.util;

public class Response {

    private final String message;
    private final ResponseStatus responseStatus;

    public ResponseStatus getStatus() {
        return this.responseStatus;
    }

    public String getMessage() {
        return this.message;
    }

    public static Response fail(String message) {
        return new Response(ResponseStatus.FAILED, message);
    }

    public static Response success(String message) {
        return new Response(ResponseStatus.SUCCESS, message);
    }

    private Response(ResponseStatus responseStatus, String message) {
        this.responseStatus = responseStatus;
        this.message = message;
    }
}
