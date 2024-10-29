package com.app.common.exception.exceptions;

import com.app.common.exception.MonthlyExpenseBusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.IM_USED)
public class AlreadyHaveException extends MonthlyExpenseBusinessException {
    public AlreadyHaveException(String message) {
        super(message);
    }
}
