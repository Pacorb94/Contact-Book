package com.contact_book.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Clase que contiene la excepción para el código 422
 * 
 * @author UserFree
 */
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableEntityException extends Exception {

	public UnprocessableEntityException(String message) {
		super(message);
	}
}
