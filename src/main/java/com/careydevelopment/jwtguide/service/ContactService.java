package com.careydevelopment.jwtguide.service;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.careydevelopment.jwtguide.model.Contact;

@Service
public class ContactService {

    public Contact fetchContactById(Integer id) {
        Contact contact = null;
        
        if (id != null) {
            switch (id) {
                case 1:
                    contact = fetchJimmyBuffet();
                    break;
                case 2:
                    contact = fetchDarthVader();
                    break;
            }
        }
        
        return contact;
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
