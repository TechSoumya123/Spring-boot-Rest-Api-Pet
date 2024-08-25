package com.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.validation.ConstraintViolationException;

public class ExceptionController {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handlerException(MethodArgumentNotValidException exception) {
		Map<String, String> errors = new HashMap<>();
		exception.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}

	@ExceptionHandler(PetNotFoundException.class)
	public ResponseEntity<Object> petNotFoundException(PetNotFoundException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<Object> handleHttpRequestMethodException(
			HttpRequestMethodNotSupportedException eSupportedException) {
		String error = "Request method is" + eSupportedException.getMethod() + ", Supported method is "
				+ eSupportedException.getSupportedHttpMethods().stream().findFirst().get();
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(error);
	}

//	public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException exception){
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getConstraintViolations().stream().map(cv->cv.getMessage()).findFirst().get());
//	}
	
}
