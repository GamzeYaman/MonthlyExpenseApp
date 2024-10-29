package com.app.common.exception;

import com.app.common.RestResponse;
import com.app.common.exception.exceptions.InvalidDataException;
import com.app.common.exception.exceptions.ItemNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;

@RestControllerAdvice
public class GeneralControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<Object> handleAllExceptions(Exception e, WebRequest webRequest) {
       return buildErrorResponse(e, webRequest, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleInvalidDataExceptions(InvalidDataException e, WebRequest webRequest) {
        return buildErrorResponse(e, webRequest, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleNotFoundException(ItemNotFoundException e, WebRequest webRequest) {
        return buildErrorResponse(e, webRequest, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<Object> buildErrorResponse(Exception e, WebRequest webRequest, HttpStatus httpStatus) {
        var restResponse = RestResponse.error(createGeneralExceptionResponse(e.getMessage(), webRequest.getDescription(true)));
        return new ResponseEntity<>(restResponse, httpStatus);
    }
    private GeneralExceptionResponse createGeneralExceptionResponse(String message, String description) {
        return new GeneralExceptionResponse(LocalDate.now(), message, description);
    }
}

