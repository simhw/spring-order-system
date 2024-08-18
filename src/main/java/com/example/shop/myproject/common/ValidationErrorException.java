package com.example.shop.myproject.common;


import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationErrorException extends RuntimeException {

    private List<ValidationError> errors = new ArrayList<>();

    public void addValidationError(ValidationError validationError) {
        this.errors.add(validationError);
    }
}
