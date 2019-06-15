
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
    @RequestMapping(value = "/list", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody 
    List<Contact> findAll() {
        return contactService.getContactList();
    }
    
    //CountContacts
    @RequestMapping(value = "/count", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody 
    int countAll() {
        return contactService.getContactList().size();
    }
    
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String getContactById(@RequestParam long id) {
    	return contactRepository.findById(id).get().toString();
    }
 
    @RequestMapping(value = "/newContact", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDTO newContact(@RequestBody ContactDTO contactDto) throws ContactAlreadyExistsException  { 
        return contactService.createContact(contactDto);
    } 
     
//  //FindOne
//  @RequestMapping(value = "/contact/{id}", method = RequestMethod.GET)
//  @ResponseBody 
//  public Contact findOne(@PathVariable Long id) {
//      Optional<Contact> contact = contactRepository.findById(id);
//      if (contact.isPresent()) {
//          return contact.get();
//      } else {
//          throw new ContactNotFoundException(id);//make find by name and share login into service
//      }
//  }
    
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


//create
//@RequestMapping(value = "/newContact", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//@ResponseBody 
//@ResponseStatus(HttpStatus.CREATED)
//Contact newContact(@RequestBody Contact newContact) {
//    return contactRepository.save(newContact);
//}

//@RequestMapping(value = "/checkContact", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//@ResponseBody
//@ResponseStatus(HttpStatus.CREATED)
//public String checkContact(@RequestBody ContactDTO contactDto)  { 
//    return convertToEntity(contactDto).toString();
//} 
//
//private Contact convertToEntity(ContactDTO contactDTO) {
//	Contact contact = new Contact(contactDTO.getName());  
//	Set<ContactEmail> contactEmails = new HashSet<>();
//	
//	for(String address : contactDTO.getEmails()) {
//		contactEmails.add(new ContactEmail(address, contact));
//	}
//	
//	contact.setContactEmails(contactEmails);
//	
//    return contact;
//}
