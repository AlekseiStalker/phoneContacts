
package com.Task.phoneContacts.controllers;

import com.Task.phoneContacts.entities.Contact;
import com.Task.phoneContacts.entities.ContactEmail;
import com.Task.phoneContacts.entities.ContactDTO.ContactDTO;
import com.Task.phoneContacts.errors.ContactNotFoundException;
import com.Task.phoneContacts.repositories.ContactRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.Task.phoneContacts.services.ContactService;

import java.text.ParseException;
import java.util.*;


@RestController
@RequestMapping(value = "/home")
public class ContactController {
	
	@Autowired
	private ContactService contactService;

    @RequestMapping(value = "/test")
    @ResponseBody
    public String index() {
        return "Hello World!";
    }

    @Autowired
    private ContactRepository contactRepository;

    //FindALL
    @RequestMapping(value = "/contacts", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody 
    List<Contact> findAll() {
        return contactRepository.findAll();
    }

    //FindOne
    @RequestMapping(value = "contact/{id}", method = RequestMethod.GET)
    @ResponseBody 
    public Contact findOne(@PathVariable Long id) {
        Optional<Contact> contact = contactRepository.findById(id);
        if (contact.isPresent()) {
            return contact.get();
        } else {
            throw new ContactNotFoundException(id);
        }
    }

    //create
//    @RequestMapping(value = "/newContact", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody 
//    @ResponseStatus(HttpStatus.CREATED)
//    Contact newContact(@RequestBody Contact newContact) {
//        return contactRepository.save(newContact);
//    }
    
   
    @RequestMapping(value = "/newContact", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDTO newContact(@RequestBody ContactDTO contactDto) {
    	Contact contact = convertToEntity(contactDto);
    	Contact contactCreated = contactService.createContact(contact);//here try catch
        return convertToDto(contactCreated);
    }
    
    
    private Contact convertToEntity(ContactDTO contactDTO) throws ParseException {
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

    // Update or save 
//    @RequestMapping(value = "/contacts/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody  
//    Contact saveOrUpdate(@RequestBody Contact newContact, @PathVariable Long id) {
//
//        return contactRepository.findById(id)
//                .map(x -> {
//                    x.setName(newContact.getName());
//                    x.setContactEmails(newContact.getContactEmails());
//                    //x.setcontactPhones(newContact.getContactPhones());
//                    return contactRepository.save(x);
//                })
//                .orElseGet(() -> {
//                    return contactRepository.save(newContact);
//                });
//    }
}
