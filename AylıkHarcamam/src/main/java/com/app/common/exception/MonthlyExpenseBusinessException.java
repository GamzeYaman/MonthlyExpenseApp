package com.app.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MonthlyExpenseBusinessException extends RuntimeException{
    private final String message;
}
