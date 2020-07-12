package com.littlehotel.littleHotelServer.utility;

import java.util.NoSuchElementException;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.littlehotel.littleHotelServer.model.ErrorResponse;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> handleEntityNotFound(EntityNotFoundException e) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
		return new ResponseEntity<Object>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<?> handleNoSuchElement(NoSuchElementException e) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
		return new ResponseEntity<Object>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<?> argumentMismatch(MethodArgumentTypeMismatchException e) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
		return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(PasswordMismatchException.class)
	public ResponseEntity<?> passwordMismatch(PasswordMismatchException e) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
		return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> invalidCredential(Exception e) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
		return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
	}

}
