package com.contact_book.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.contact_book.app.model.Contact;
import com.contact_book.app.repository.ContactRepository;
import com.contact_book.app.service.interfaces.ContactServiceInterface;

/**
 * Clase que separa la lógica del controlador y además esta clase sigue el
 * patrón facade
 */
@Service
public class ContactService implements ContactServiceInterface {

	@Autowired
	private ContactRepository contactRepo;

	@Override
	@Transactional
	public Contact save(Contact contact) {
		return this.contactRepo.save(contact);	
	}

	@Override
	@Transactional(readOnly = true)
	public List<Contact> getAll() {
		return this.contactRepo.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Contact getContact(long id) {
		Optional<Contact> opContact = this.contactRepo.findById(id);
		if (opContact.isPresent()) {
			return opContact.get();
		}
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Contact getContactByEmail(String email) {
		Optional<Contact> opContact = this.contactRepo.findByEmail(email);
		if (opContact.isPresent()) {
			return opContact.get();
		}
		return null;
	}

	@Override
	@Transactional
	public void delete(long id) {
		this.contactRepo.deleteById(id);
	}
}
