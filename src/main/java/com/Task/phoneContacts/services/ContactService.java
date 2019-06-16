package com.Task.phoneContacts.services;

import com.Task.phoneContacts.entities.Contact;
import com.Task.phoneContacts.entities.ContactEmail;
import com.Task.phoneContacts.entities.ContactDTO.ContactDTO;

import java.util.List;
import java.util.Set;

public interface ContactService {
 
    ContactDTO createContact(ContactDTO contactDto);
    	  
    ContactDTO getContactById(Long id);
    
    ContactDTO updateContactById(Long id, ContactDTO contactDto); 
    
    ContactDTO deleteContactById(Long id); 

    List<ContactDTO> getContacts();
}
