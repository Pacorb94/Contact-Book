package com.contact_book.app.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.contact_book.app.dao.ContactRepository;
import com.contact_book.app.models.Contact;


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
	public List<Contact> getContacts() {
		return this.contactRepo.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Contact> getContact(Long id) {
		return this.contactRepo.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Contact getContactByEmail(String email) {
		return this.contactRepo.findByEmail(email);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		this.contactRepo.deleteById(id);
	}

	@Override
	@Transactional
	public void deleteByEmail(String email) {
		this.contactRepo.deleteByEmail(email);
	}
}
