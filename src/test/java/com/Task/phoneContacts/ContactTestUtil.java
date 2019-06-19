package com.Task.phoneContacts;

import java.util.HashSet;
import java.util.Set;

import com.Task.phoneContacts.dto.*;
import com.Task.phoneContacts.model.Contact;
import com.Task.phoneContacts.model.ContactEmail;
import com.Task.phoneContacts.model.ContactPhone;

public class ContactTestUtil {
	
	public static ContactDTO createDTO(String name, String[] emails, String[] phones) {
		ContactDTO dto = new ContactDTO();
		
		dto.setName(name);
		dto.setEmails(emails);
		dto.setPhones(phones);
		
		return dto;
	}
	
	public static Contact createContact(Long id, String name, String[] emails, String[] phones) {
		Contact contact = new Contact(name);
		
		Set<ContactEmail> contactEmails = new HashSet<>();
    	Set<ContactPhone> contactPhones = new HashSet<>();
    	
    	for(String address : emails) {
    		contactEmails.add(new ContactEmail(address, contact));
    	} 
    	for(String number : phones) {
    		contactPhones.add(new ContactPhone(number, contact));
    	} 
    	
    	contact.setContactEmails(contactEmails);
    	contact.setContactPhones(contactPhones);
    	
        return contact;
	}
}
