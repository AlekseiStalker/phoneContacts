
package com.Task.phoneContacts.controllers;

import com.Task.phoneContacts.entities.Contact;
import com.Task.phoneContacts.errors.ContactNotFoundException;
import com.Task.phoneContacts.repositories.ContactRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.Task.phoneContacts.services.ContactService;

import java.util.List;
import java.util.Optional;

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
    
   
//    @RequestMapping(value = "/newContact", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//     
//    @ResponseStatus(HttpStatus.CREATED)
//    public String newContact(@RequestBody String contactName, ) {
//    	Contact nContact = contactService.createContact(contact, contactEmails);
//        return "";
//    }

    // Update or save 
    @RequestMapping(value = "/contacts/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody  
    Contact saveOrUpdate(@RequestBody Contact newContact, @PathVariable Long id) {

        return contactRepository.findById(id)
                .map(x -> {
                    x.setName(newContact.getName());
                    x.setContactEmails(newContact.getContactEmails());
                    //x.setcontactPhones(newContact.getContactPhones());
                    return contactRepository.save(x);
                })
                .orElseGet(() -> {
                    return contactRepository.save(newContact);
                });
    }
}
