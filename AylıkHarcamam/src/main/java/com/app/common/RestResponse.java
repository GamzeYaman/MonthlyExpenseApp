package com.app.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class RestResponse<T> {
    private T data;
    private LocalDateTime responseDate;
    private boolean isSuccess;
    private String message;

    public RestResponse(T data, boolean isSuccess) {
        this.data = data;
        this.isSuccess = isSuccess;
        this.responseDate = LocalDateTime.now();
    }

    public static <T> RestResponse<T> of(T t) {
        return new RestResponse<>(t, true);
    }

    public static <T> RestResponse<T> error(T t) {
        return new RestResponse<>(t, false);
    }
}