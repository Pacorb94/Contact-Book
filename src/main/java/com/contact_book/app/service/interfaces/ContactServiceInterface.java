package com.contact_book.app.service.interfaces;

import java.util.*;

import com.contact_book.app.model.Contact;


public interface ContactServiceInterface {

	public Contact save(Contact contact);

	public List<Contact> getAll();

	public Optional<Contact> getContact(Long id);

	public Optional<Contact> getContactByEmail(String email);

	public void delete(Long id);

	public void deleteByEmail(String email);
}
