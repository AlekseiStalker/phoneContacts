package com.Task.phoneContacts.services.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Set;

import com.Task.phoneContacts.entities.Contact;
import com.Task.phoneContacts.entities.ContactEmail;
import com.Task.phoneContacts.entities.ContactDTO.ContactDTO;
import com.Task.phoneContacts.errors.ContactAlreadyExistsException;
import com.Task.phoneContacts.repositories.ContactRepository;
import com.Task.phoneContacts.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.HashSet;
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
    
//    @Override
//    public Contact createContact(Contact contact, Set<ContactEmail> contactEmails) {
//    	Contact tempContact = contactRepository.findByName(contact.getName());
//    	
//    	if(tempContact != null) {
//    		LOG.info("Contact with name {} already exist. Nothing will be done. ", contact.getName());
//    	} else {
//    		contact.setContactEmails(contactEmails); 
//    	}
//    	return contactRepository.save(contact);
//    }
    
    @Override
    public ContactDTO createContact(ContactDTO newContact) { 
    	 
    	if(checkContactExists(newContact.getName())) {
    		throw new ContactAlreadyExistsException(newContact.getName());
    	} else {
//    		Contact contact = new Contact(newContact.getName());
//    		
//    		Set<ContactEmail> cEmails = new HashSet<>();
//    		for(String emailAddress : newContact.getEmails()) {
//    			cEmails.add(new ContactEmail(emailAddress, contact));
//    		}
//    		
//    		contact.setContactEmails(cEmails); 
    		
    		Contact contact = convertToEntity(newContact);
        	Contact contactCreated = contactRepository.save(contact);
            return convertToDto(contactCreated);
 
    	}
    }
    
    private Contact convertToEntity(ContactDTO contactDTO) {
    	Contact contact = new Contact(contactDTO.getName());  
    	Set<ContactEmail> contactEmails = new HashSet<>();
    	
    	for(String address : contactDTO.getEmails()) {
    		contactEmails.add(new ContactEmail(address, contact));
    	}
    	
        return contact;
    }
     
    private ContactDTO convertToDto(Contact contact) {
        ContactDTO contactDto = new ContactDTO();  
        contactDto.setName(contact.getName());
         
        String contactEmails[] = new String[contact.getContactEmails().size()]; 
        int i = 0;
        for(ContactEmail e: contact.getContactEmails()) {
        	contactEmails[i++] = e.getEmail();
        }
        
        contactDto.setEmails(contactEmails);
        
        return contactDto;
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

//	@Override
//	public Contact createContact(ContactDTO contact) {
//		Contact resContact = new Contact(contact.getName());
//		
//		Set<ContactEmail> cEmails = new HashSet<>();
//		for(String emailAddress : contact.getEmails()) {
//			cEmails.add(new ContactEmail(emailAddress, resContact));
//		}
//	
//		resContact.setContactEmails(cEmails); 
//		
//		return contactRepository.save(resContact); 
//	}
 
}
