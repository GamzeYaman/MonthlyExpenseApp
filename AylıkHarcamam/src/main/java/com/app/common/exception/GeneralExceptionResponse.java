package com.app.common.exception;

import java.time.LocalDate;

public record GeneralExceptionResponse(LocalDate date, String message, String description) {
}
