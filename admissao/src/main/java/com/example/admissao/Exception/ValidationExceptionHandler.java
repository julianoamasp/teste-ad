package com.example.admissao.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ValidationExceptionHandler {
	   @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
	        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
	                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
	                .reduce("", (a, b) -> a + "\n" + b);
	        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
	    }
}
