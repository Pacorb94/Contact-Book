package com.contact_book.app.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.contact_book.app.exception.UnprocessableEntityException;
import com.contact_book.app.model.Contact;


/**
 * Clase que valida la petición cuando crea o se edita un contacto
 * 
 * @author UserFree
 */
@Component
public class ContactValidator {

	public void validator(@RequestBody Contact request) throws UnprocessableEntityException {
		if (!request.getEmail().trim().isEmpty()) {
			final String REGEX = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
			if (!Pattern.matches(REGEX, request.getEmail())) {
				this.message("Email isn`t correct");
			}
			//TODO: comprobar si existe el email de la petición
		} else {
			this.message("Email is required");
		}

		if (request.getName().trim().isEmpty()) {
			this.message("Name is required");
		}

		if (request.getAge() <= 17) {
			this.message("Age min 18");
		}
	}

	private void message(String message) throws UnprocessableEntityException {
		throw new UnprocessableEntityException(message);
	}
}
