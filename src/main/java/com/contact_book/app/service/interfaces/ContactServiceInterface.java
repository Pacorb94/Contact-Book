package com.contact_book.app.service.interfaces;

import java.util.List;

import com.contact_book.app.model.Contact;


public interface ContactServiceInterface {

	public Contact save(Contact contact);

	public List<Contact> getAll();

	public Contact getContact(Long id);
	
	public Contact getContactByEmail(String email);

	public void delete(Long id);
}
