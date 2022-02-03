package com.contact_book.app.exceptions;

import java.util.List;

/**
 * Clase que sirve de modelo para hacer una respuesta
 * de error
 */
@SuppressWarnings("serial")
public class ErrorResponse extends RuntimeException {
	private String message;
	private List<String> details;

	public ErrorResponse(String message, List<String> details) {
		super();
		this.message = message;
		this.details = details;
	}
	
	@Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}
}
