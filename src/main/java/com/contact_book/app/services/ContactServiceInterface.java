package com.contact_book.app.services;

import java.util.*;

import com.contact_book.app.models.Contact;


public interface ContactServiceInterface {

	public Contact save(Contact contact);

	public List<Contact> getContacts();

	public Optional<Contact> getContact(Long id);

	public Contact getContactByEmail(String email);

	public void delete(Long id);

	public void deleteByEmail(String email);
}
