/**
 * 
 */
package com.imaginnovate.assignment.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Ajay Sarvasiddhi
 *
 */
public class ApiException {
	private HttpStatus status;
	private String message;
	private String debugMessage;

	ApiException(HttpStatus status) {
		this.status = status;
	}

	ApiException(HttpStatus status, Throwable ex) {
		this.status = status;
		this.message = "Unexpected error";
		this.debugMessage = ex.getLocalizedMessage();
	}

	ApiException(HttpStatus status, String message, Throwable ex) {
		this.status = status;
		this.message = message;
		this.debugMessage = ex.getLocalizedMessage();
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

	public String getDebugMessage() {
		return debugMessage;
	}

	public void setDebugMessage(String debugMessage) {
		this.debugMessage = debugMessage;
	}

}
