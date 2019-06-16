package com.Task.phoneContacts.services.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Set;

import com.Task.phoneContacts.entities.Contact;
import com.Task.phoneContacts.entities.ContactEmail;
import com.Task.phoneContacts.entities.ContactDTO.ContactDTO;
import com.Task.phoneContacts.errors.ContactAlreadyExistsException;
import com.Task.phoneContacts.errors.EmailAlreadyExistsException;
import com.Task.phoneContacts.repositories.ContactRepository;
import com.Task.phoneContacts.services.ContactService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import java.util.*;

@Service
public class ContactServiceImpl implements ContactService {

	private static final Logger LOG = LoggerFactory.getLogger(ContactService.class);
	
    @Autowired
    private ContactRepository contactRepository;
 
    @Override
    public ContactDTO createContact(ContactDTO contactDto) { 
    	 
    	if(!isNameUnique(contactDto.getName())) {
    		throw new ContactAlreadyExistsException(contactDto.getName());
    	} else {  
    		Contact contact = convertToEntity(contactDto);
        	Contact createdContact = contactRepository.save(contact);
            return convertToDto(createdContact); 
    	}
    } 
    
	@Override
	public ContactDTO getContactById(Long id) {
		Optional<Contact> contact = contactRepository.findById(id);
		return convertToDto(contact.get());
	}
 
	@Override
	public ContactDTO updateContactById(Long id, ContactDTO contactDto) { 
		if(!isNameUnique(contactDto.getName())) {
			throw new ContactAlreadyExistsException(contactDto.getName());
		}

		Contact oldContact = contactRepository.findById(id).get();
		
		if(!isEmailUnique(oldContact.getContactEmails(), contactDto.getEmails())) {
			throw new EmailAlreadyExistsException();
		} 
		//if() make for phones
		
		Contact newContact = convertToEntity(contactDto); 
		
		BeanUtils.copyProperties(newContact, oldContact);
		 
		Contact updatedContact = contactRepository.save(oldContact);
		
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
	public ContactDTO deleteContactById(Long id) {
		Contact contact = contactRepository.findById(id).get();
		contactRepository.deleteById(id);
		return convertToDto(contact);
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
    	ContactEmail[] oldEmailsArr = (ContactEmail[])oldEmails.toArray();
    	List<String> newEmailsList = Arrays.asList(newEmails);
    	
    	for(int i = 0; i < newEmails.length; i++) {
    		if(newEmailsList.contains(oldEmailsArr[i].getAddress())) {
    			return false;
    		}
    	}
    	return true;
    } 
     
    private Contact findByContactName(String name) {
        return contactRepository.findByName(name);
    }
}
