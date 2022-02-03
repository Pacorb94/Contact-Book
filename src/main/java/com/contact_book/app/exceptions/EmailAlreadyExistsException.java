package com.contact_book.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class EmailAlreadyExistsException extends RuntimeException {
	
	public EmailAlreadyExistsException(String message) {
		super(message);
	}

}
