package com.ecommerce.shopping_api.exception.advice;

import com.ecommerce.shopping_client.dto.ErrorDTO;
import com.ecommerce.shopping_client.exception.ProductNotFoundException;
import com.ecommerce.shopping_client.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice(basePackages = "com.ecommerce.shopping_api.controller")
public class ShoppingControllerAdvice {
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleProductNotFound(ProductNotFoundException ex) {
        return new ErrorDTO(404, ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleUserNotFound(UserNotFoundException ex) {
        return new ErrorDTO(404, ex.getMessage(), LocalDateTime.now());
    }
}