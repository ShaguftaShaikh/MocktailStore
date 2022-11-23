package com.api.mocktailstore.exceptions;

import com.api.mocktailstore.util.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserAlreadyExists.class)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Response userAlreadyExistsException(
            UserAlreadyExists userAlreadyExists, WebRequest request) {
        return Response.fail("User with email: " + userAlreadyExists.getEmail() + " already exists!");
    }

}
