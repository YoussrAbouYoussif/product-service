package com.udin.tech.task.product_service.validation;

import com.udin.tech.task.product_service.exception.NullCriteriaException;
import com.udin.tech.task.product_service.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppError {

    @ExceptionHandler(NullCriteriaException.class)
    public ResponseEntity<String> handleNullCriteria(NullCriteriaException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleNotFound(ProductNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
