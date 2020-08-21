package com.careydevelopment.jwtguide.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.careydevelopment.jwtguide.model.Contact;
import com.careydevelopment.jwtguide.service.ContactService;

@RestController
public class ContactController {

    @Autowired
    private ContactService contactService;
    
    
    @GetMapping("/contact")
    public ResponseEntity<Contact> contact(@RequestParam(required = false) Integer id) {
        HttpStatus status = HttpStatus.OK;
        Contact contact = contactService.fetchContactById(id);
        
        if (contact == null) {
            status = HttpStatus.NOT_FOUND;
        }
        
        return ResponseEntity.status(status).body(contact);
    }
    
    
    @GetMapping("/contactBySalesOwner")
    public ResponseEntity<Contact> contactBySalesOwner(@RequestParam Integer id, @RequestParam Integer salesOwnerId) {
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
