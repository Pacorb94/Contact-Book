package com.contact_book.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.contact_book.app.exceptions.EmailAlreadyExistsException;
import com.contact_book.app.exceptions.ModelNotFoundException;
import com.contact_book.app.model.Contact;
import com.contact_book.app.service.ContactService;


@RestController
@RequestMapping("/contacts")
public class ContactController {

	@Autowired
	private ContactService contactService;

	@PostMapping
	public ResponseEntity<Contact> create(@Valid @RequestBody Contact request) {
		Contact contact=this.contactService.getContactByEmail(request.getEmail());
		if(contact == null) {	
			return ResponseEntity.created(null).body(this.contactService.save(request));		
		}
		throw new EmailAlreadyExistsException("Email already exists");
	}

	@GetMapping
	public ResponseEntity<List<Contact>> getAll() {
		return ResponseEntity.ok(this.contactService.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Contact> getContact(@PathVariable Long id) {
		Contact contact = this.contactService.getContact(id);
		if (contact != null) {
			return ResponseEntity.ok(contact);
		}
		throw new ModelNotFoundException("Model not found");
	}

	@PutMapping("/{id}")
	public ResponseEntity<Contact> update(@Valid @RequestBody Contact request, @PathVariable Long id) {
		Contact contact = this.contactService.getContact(id);
		if (contact != null) {
			BeanUtils.copyProperties(request, contact);
			return ResponseEntity.ok(this.contactService.save(contact));
		}
		throw new ModelNotFoundException("Model not found");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		Contact contact = this.contactService.getContact(id);
		if (contact != null) {
			this.contactService.delete(id);
			return ResponseEntity.noContent().build();
		}
		throw new ModelNotFoundException("Model not found");
	}
}
