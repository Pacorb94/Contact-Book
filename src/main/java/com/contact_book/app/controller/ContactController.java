package com.contact_book.app.controller;

import java.util.*;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.contact_book.app.exception.UnprocessableEntityException;
import com.contact_book.app.model.Contact;
import com.contact_book.app.service.ContactService;
import com.contact_book.app.validator.ContactValidator;


@RestController
@RequestMapping("/contacts")
public class ContactController {

	@Autowired
	private ContactService contactService;

	@Autowired
	private ContactValidator contactValidator;

	@PostMapping
	public ResponseEntity<Contact> create(@RequestBody Contact request) throws UnprocessableEntityException {
		this.contactValidator.validator(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(this.contactService.save(request));			
	}

	@GetMapping
	public List<Contact> getAll() {
		return this.contactService.getAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Contact> getContact(@PathVariable Long id) {
		Contact contact = this.contactService.getContact(id);
		if (contact != null) {
			return ResponseEntity.ok(contact);
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Contact> update(
			@RequestBody Contact request, 
			@PathVariable Long id
	) throws UnprocessableEntityException {
		this.contactValidator.validator(request);
		Contact contact = this.contactService.getContact(id);
		if (contact != null) {
			BeanUtils.copyProperties(request, contact);
			return ResponseEntity.ok(this.contactService.save(contact));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		Contact contact = this.contactService.getContact(id);
		if (contact != null) {
			this.contactService.delete(id);
			Map<String, Object> response = new HashMap<>();
			response.put("message", "Deleted contact");
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.notFound().build();
	}
}
