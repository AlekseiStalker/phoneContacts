package com.Task.phoneContacts.services.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Set;

import com.Task.phoneContacts.entities.Contact;
import com.Task.phoneContacts.entities.ContactEmail;
import com.Task.phoneContacts.repositories.ContactRepository;
import com.Task.phoneContacts.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

	private static final Logger LOG = LoggerFactory.getLogger(ContactService.class);
	
    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Contact findByContactName(String name) {
        return contactRepository.findByName(name);
    }
    
    @Override
    public Contact createContact(Contact contact, Set<ContactEmail> contactEmails) {
    	Contact tempContact = contactRepository.findByName(contact.getName());
    	
    	if(tempContact != null) {
    		LOG.info("Contact with name {} already exist. Nothing will be done. ", contact.getName());
    	} else {
    		contact.setContactEmails(contactEmails); 
    	}
    	return contactRepository.save(contact);
    }

    @Override
    public boolean checkContactExists(String name) {
        if (findByContactName(name) != null) {
            return true;
        } else {
            return false;
        }
    } 

    @Override
    public Contact saveContact(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public void deleteContact(String name) {
        contactRepository.deleteContactByName(name);
    }

    @Override
    public List<Contact> getContactList() {
        return contactRepository.findAll();
    }

	@Override
	public boolean checkEmailExistsForContact(Contact contact, String email) {
		Set<ContactEmail> contactEmails = contact.getContactEmails();
		
		for(ContactEmail e : contactEmails) {
			if(e.getEmail() == email) {
				return true;
			}
		} 
		return false;
	}
 
}
