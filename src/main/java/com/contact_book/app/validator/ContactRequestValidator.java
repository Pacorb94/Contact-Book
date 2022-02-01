package com.contact_book.app.validator;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.contact_book.app.exception.UnprocessableEntityException;
import com.contact_book.app.model.Contact;
import com.contact_book.app.service.ContactService;

/**
 * Clase que valida la petición cuando crea o se edita un contacto
 * 
 * @author UserFree
 */
@Component
public class ContactRequestValidator {

	@Autowired
	private ContactService contactService;

	public void validator(@RequestBody Contact request, Long id) throws UnprocessableEntityException {
		if (!request.getEmail().isEmpty()) {
			if (!this.isCorrectEmail(request.getEmail())) {
				this.message("Email isn`t correct");
			}
			if (this.emailExists(request.getEmail(), id)) {
				this.message("Email is already exists");
			}
		} else {
			this.message("Email is required");
		}

		if (request.getName().isEmpty()) {
			this.message("Name is required");
		}

		if (request.getAge() <= 17) {
			this.message("Age min 18");
		}
	}

	private Boolean isCorrectEmail(String email) {
		final String REGEX = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
		if (!Pattern.matches(REGEX, email)) {
			return false;
		}
		return true;
	}

	private Boolean emailExists(String email, Long id) {
		Contact contactAux = this.contactService.getContactByEmail(email);
		if (contactAux != null && contactAux.getId() != id) {
			return true;
		}
		return false;
	}

	private void message(String message) throws UnprocessableEntityException {
		throw new UnprocessableEntityException(message);
	}
}
