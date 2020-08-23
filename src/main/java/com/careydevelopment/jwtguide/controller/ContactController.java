package com.careydevelopment.jwtguide.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.careydevelopment.jwtguide.model.Contact;
import com.careydevelopment.jwtguide.service.ContactService;

@RestController
@RequestMapping("/contact")
public class ContactController {

	@Autowired
	private ContactService contactService;
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Contact> contact(@PathVariable Integer id) {
		HttpStatus status = HttpStatus.OK;
		Contact contact = contactService.fetchContactById(id);
		
		if (contact == null) {
			status = HttpStatus.NOT_FOUND;
		}

		return ResponseEntity.status(status).body(contact);
	}

	
	@GetMapping("/{salesOwnerId}/{id}")
	public ResponseEntity<Contact> contactBySalesOwner(@PathVariable Integer salesOwnerId, @PathVariable Integer id) {
		HttpStatus status = HttpStatus.OK;
		Contact contact = null;
		
		Optional<Contact> contactOptional = contactService.fetchContactBySalesOwnerIdAndId(salesOwnerId, id);
		
		if (!contactOptional.isPresent()) {
			status = HttpStatus.NOT_FOUND;
		} else {
			contact = contactOptional.get();
		}

		return ResponseEntity.status(status).body(contact);
	}
	
}
