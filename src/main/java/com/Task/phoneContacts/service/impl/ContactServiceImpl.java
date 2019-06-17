package com.Task.phoneContacts.service.impl;
 
import java.util.Set;

import com.Task.phoneContacts.model.*;
import com.Task.phoneContacts.dto.ContactDTO;
import com.Task.phoneContacts.error.*;
import com.Task.phoneContacts.repository.ContactRepository;
import com.Task.phoneContacts.service.ContactService;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import java.util.*;

@Service
public class ContactServiceImpl implements ContactService {
 
    @Autowired
    private ContactRepository contactRepository;
 
    @Override
    public ContactDTO createContact(ContactDTO contactDto) {  
    	if(!isNameUnique(contactDto.getName())) {
    		throw new ContactAlreadyExistsException(contactDto.getName());
    	} 
    	
    	Contact createdContact = convertToEntity(contactDto);
    	createdContact = contactRepository.save(createdContact);
    	
        return convertToDto(createdContact); 
    } 
    
	@Override
	public ContactDTO getContactById(Long id) {
		Optional<Contact> contact = contactRepository.findById(id); 
		
		if (!contact.isPresent()) {
			throw new ContactNotFoundException(id);
		} 
		
		return convertToDto(contact.get());
	}
 
	@Override
	public ContactDTO updateContactById(Long id, ContactDTO contactDto) { 
		if(!isNameUnique(contactDto.getName())) {
			throw new ContactAlreadyExistsException(contactDto.getName());
		}

		Contact contact = contactRepository.findById(id).get();
		 
		if(!isEmailUnique(contact.getContactEmails(), contactDto.getEmails())) {
			throw new EmailAlreadyExistsException();
		} 
		//if() make for phones
		 
		updateContactProperties(contactDto, contact);
		 
		Contact updatedContact = contactRepository.save(contact);
		
		return convertToDto(updatedContact);
	}  
 
    @Override
    public List<ContactDTO> getContacts() {
    	List<Contact> contacts = contactRepository.findAll();
    	
    	List<ContactDTO> contatcsDto = new ArrayList<>();
    	for(Contact c : contacts)
    		contatcsDto.add(convertToDto(c));
    	
        return contatcsDto;
    }
     
	@Override
	public boolean deleteContactById(Long id) {
		Contact contact = contactRepository.findById(id).get();
		contactRepository.deleteById(id);
		return true;
	} 
    
    private Contact convertToEntity(ContactDTO contactDTO) {
    	Contact contact = new Contact(contactDTO.getName());  
    	Set<ContactEmail> contactEmails = new HashSet<>();
    	
    	for(String address : contactDTO.getEmails()) {
    		contactEmails.add(new ContactEmail(address, contact));
    	} 
    	
    	contact.setContactEmails(contactEmails);
    	
        return contact;
    }
     
    private ContactDTO convertToDto(Contact contact) {
        ContactDTO contactDto = new ContactDTO();  
        contactDto.setName(contact.getName());
         
        String contactEmails[] = new String[contact.getContactEmails().size()]; 
        int i = 0;
        for(ContactEmail e: contact.getContactEmails()) {
        	contactEmails[i++] = e.getAddress();
        }
        
        contactDto.setEmails(contactEmails);
        
        return contactDto;
    }

    private boolean isNameUnique(String name) {
        return findByContactName(name) == null;
    }  
      
    private boolean isEmailUnique(Set<ContactEmail> oldEmails, String... newEmails) { 
    	ContactEmail[] oldEmailsArr = oldEmails.toArray(new ContactEmail[0]);
    	List<String> newEmailsList = Arrays.asList(newEmails);
    	
    	for(int i = 0; i < newEmails.length; i++) {
    		if(newEmailsList.contains(oldEmailsArr[i].getAddress())) {
    			return false;
    		}
    	}
    	return true;
    } 
    
    private void updateContactProperties(ContactDTO newContact, Contact oldContact) {
    	if(newContact.getName() != "") {
    		oldContact.setName(newContact.getName());
    	}
    	
    	if(newContact.getEmails().length != 0) {
    		Set<ContactEmail> contactEmails = oldContact.getContactEmails(); 
    		String[] newEmails = newContact.getEmails();
    		for(int i = 0; i < newEmails.length; i++) {
    			contactEmails.add(new ContactEmail(newEmails[i], oldContact));
    		}
    		oldContact.setContactEmails(contactEmails);
    	}
    	
    	//make for phones
    }
     
    private Contact findByContactName(String name) {
        return contactRepository.findByName(name);
    }
}
