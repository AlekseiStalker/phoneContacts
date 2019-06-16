
package com.Task.phoneContacts.controllers;

import com.Task.phoneContacts.entities.Contact;
import com.Task.phoneContacts.entities.ContactEmail;
import com.Task.phoneContacts.entities.ContactDTO.ContactDTO;
import com.Task.phoneContacts.errors.ContactAlreadyExistsException;
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
@RequestMapping(value = "/api/contact")
public class ContactController {
	
	@Autowired
	private ContactService contactService;
	
	//----------------------------
	
    @RequestMapping(value = "/test")
    @ResponseBody
    public String index() {
        return "Hello World!";
    } 
   
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ResponseBody 
    int countAll() {
        return contactService.getContacts().size();
    }

    //----------------------------
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<ContactDTO> list() {
    	return contactService.getContacts();
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDTO create(@RequestBody ContactDTO contactDto) { 
        return contactService.createContact(contactDto);
    } 
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ContactDTO get(@PathVariable("id") Long id) {
    	return contactService.getContactById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE) 
    @ResponseStatus(HttpStatus.OK)
	public ContactDTO update(@PathVariable("id") Long id, @RequestBody ContactDTO contactDto) {
		return contactService.updateContactById(id, contactDto);
	}
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE) 
    @ResponseStatus(HttpStatus.OK)
	public ContactDTO delete(@PathVariable("id") Long id) {
		return contactService.deleteContactById(id);
	}
}