package com.workintech.spring17challenge.exceptions;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Component
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ApiErrorResponse> exceptionHandler(ApiException exception){
        ApiErrorResponse apiResponse = new ApiErrorResponse(exception.getMessage(), System.currentTimeMillis(), exception.getHttpStatus());
        return new ResponseEntity(apiResponse , exception.getHttpStatus());
    }
}
