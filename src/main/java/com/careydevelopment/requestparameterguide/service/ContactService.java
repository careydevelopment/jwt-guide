package com.careydevelopment.requestparameterguide.service;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.careydevelopment.requestparameterguide.model.Contact;

@Service
public class ContactService {

    public Contact fetchContactById(Integer id) {
        switch (id) {
            case 1:
                return fetchJimmyBuffet();
            case 2:
                return fetchDarthVader();
            default:
                return null;
        }
    }
	
	
    public Optional<Contact> fetchContactBySalesOwnerIdAndId(Integer salesOwnerId, Integer id) {
        Optional<Contact> contact = Stream.of(fetchJimmyBuffet(), fetchDarthVader())
            .filter(c -> c.getSalesOwnerId().equals(salesOwnerId))
            .filter(c -> c.getId().equals(id))
            .findFirst();
        
        return contact;
    }
	
	
    private Contact fetchJimmyBuffet() {
        Contact contact = new Contact();
        
        contact.setId(1);
        contact.setAuthority(true);
        contact.setEmail("jimmy.buffet@xmail.com");
        contact.setFirstName("James");
        contact.setLastName("Buffet");
        
        return contact;
    }

	
    private Contact fetchDarthVader() {
        Contact contact = new Contact();
        
        contact.setId(2);
        contact.setAuthority(true);
        contact.setEmail("darth.vader@xmail.com");
        contact.setFirstName("Darth");
        contact.setLastName("Vader");
        
        return contact;
    }

}
