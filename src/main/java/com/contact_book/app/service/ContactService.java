package com.contact_book.app.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.contact_book.app.model.Contact;
import com.contact_book.app.repository.ContactRepository;
import com.contact_book.app.service.interfaces.ContactServiceInterface;


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
	public Optional<Contact> getContact(Long id) {
		return this.contactRepo.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Contact> getContactByEmail(String email) {
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
