package com.app.common.exception.exceptions;

import com.app.common.exception.MonthlyExpenseBusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidDataException extends MonthlyExpenseBusinessException {
    public InvalidDataException(String message) {
        super(message);
    }
}
