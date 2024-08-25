package com.example.shop.myproject.common;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiResponse<T> {
    private String message;
    private int code;
    private T data;

    protected ApiResponse() {
    }

    public ApiResponse(HttpStatus status, T data) {
        this.message = status.isError() ? "FAIL" : "SUCCESS";
        this.code = status.value();
        this.data = data;
    }
}
