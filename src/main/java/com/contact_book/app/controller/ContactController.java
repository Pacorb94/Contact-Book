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
import com.contact_book.app.request.ContactRequest;
import com.contact_book.app.service.ContactService;

@RestController
@RequestMapping("/contacts")
public class ContactController {

	@Autowired
	private ContactService contactService;

	@PostMapping
	public ResponseEntity<Contact> create(@Valid @RequestBody ContactRequest request) {
		if(this.contactService.getContactByEmail(request.getEmail()) == null) {	
			Contact contact=new Contact();
			contact.setAllProperties(request);
			return ResponseEntity.created(null).body(this.contactService.save(contact));		
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
		throw new ModelNotFoundException("Contact not found");
	}

	@PutMapping("/{id}")
	public ResponseEntity<Contact> update(
			@Valid @RequestBody ContactRequest request, 
			@PathVariable Long id
	) {
		Contact contact = this.contactService.getContact(id);
		if (contact != null) {
			Contact contactAux=this.contactService.getContactByEmail(request.getEmail());
			/*Comprobamos si el email ya existe y si el id es distinto al que 
			vamos a modificar*/
			if(contactAux != null && contactAux.getId() != id) {
				throw new EmailAlreadyExistsException("Email already exists");
			}
			BeanUtils.copyProperties(request, contact);
			return ResponseEntity.ok(this.contactService.save(contact));
		}
		throw new ModelNotFoundException("Contact not found");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		Contact contact = this.contactService.getContact(id);
		if (contact != null) {
			this.contactService.delete(id);
			return ResponseEntity.noContent().build();
		}
		throw new ModelNotFoundException("Contact not found");
	}
}
