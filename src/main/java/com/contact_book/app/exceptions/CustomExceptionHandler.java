package com.contact_book.app.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.*;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Clase que maneja las excepciones
 */
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	public CustomExceptionHandler() {
		
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleServerExceptions(Exception ex) {
		List<String> details = new ArrayList<>();
		ErrorResponse error = new ErrorResponse("Server Error", details);
		return new ResponseEntity<Object>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ModelNotFoundException.class)
	public ResponseEntity<Object> handleModelNotFoundException(
			ModelNotFoundException ex
	) {
		List<String> details = new ArrayList<>();
		ErrorResponse error = new ErrorResponse("Contact not found", details);
		return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ResponseEntity<Object> handleEmailAlreadyExistsException(
			EmailAlreadyExistsException ex
	){
		List<String> details = new ArrayList<>();
		ErrorResponse error = new ErrorResponse("Email already exists", details);
		return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex,
			HttpHeaders headers, 
			HttpStatus status, 
			WebRequest request
	) {
		List<String> details = new ArrayList<>();
		//Añadimos los mensajes de error
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			details.add(error.getDefaultMessage());
		}
		ErrorResponse error = new ErrorResponse("Validation Failed", details);
		return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
	}
}
