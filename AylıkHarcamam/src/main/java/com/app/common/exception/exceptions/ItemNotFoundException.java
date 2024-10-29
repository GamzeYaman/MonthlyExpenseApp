package com.app.common.exception.exceptions;

import com.app.common.exception.MonthlyExpenseBusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ItemNotFoundException extends MonthlyExpenseBusinessException {
    public ItemNotFoundException(String message) {
        super(message);
    }
}
