package com.Reviews.util;

import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestControllerAdvice {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> exceptionHandler(MethodArgumentNotValidException exception) {
			
			
			String errorMsg = exception.getBindingResult().getAllErrors().stream().map(x -> x.getDefaultMessage())
					.collect(Collectors.joining(", "));
			
		
			return new ResponseEntity<>(errorMsg, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<String> pathExceptionHandler(ConstraintViolationException exception) {
		
		String errorMsg = exception.getConstraintViolations().stream().map(x -> x.getMessage())
				.collect(Collectors.joining(", "));
		
		return new ResponseEntity<>(errorMsg, HttpStatus.BAD_REQUEST);
	}

}
