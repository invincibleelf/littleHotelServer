package com.littlehotel.littleHotelServer.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Response class for generating validation error messages
 * 
 * @author Sharad Shrestha
 *
 */
public class ErrorResponse {

	private HttpStatus status;

	private String message;

	@JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime dateTime;

	private String rejectedValue;

	private List<SubError> errors;

	public ErrorResponse() {
		this.dateTime = LocalDateTime.now();
	}

	public ErrorResponse(HttpStatus status, String message) {
		this();
		this.status = status;
		this.message = message;
	}

	public ErrorResponse(HttpStatus status, String message, String rejectedValue) {
		this();
		this.status = status;
		this.message = message;
		this.rejectedValue = rejectedValue;
	}

	public ErrorResponse(HttpStatus status, String message, List<SubError> errors) {
		this();
		this.status = status;
		this.message = message;
		this.errors = errors;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRejectedValue() {
		return rejectedValue;
	}

	public void setRejectedValue(String rejectedValue) {
		this.rejectedValue = rejectedValue;
	}

	public List<SubError> getErrors() {
		return errors;
	}

	public void setErrors(List<SubError> errors) {
		this.errors = errors;
	}

}
