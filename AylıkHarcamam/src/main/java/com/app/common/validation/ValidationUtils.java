package com.app.common.validation;

public class ValidationUtils {

    public static boolean isValueNull(String value) {
        return value.isEmpty() || value.isBlank();
    }
}
