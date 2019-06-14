
package com.Task.phoneContacts.controllers;

import com.Task.phoneContacts.entities.Contact;
import com.Task.phoneContacts.errors.ContactNotFoundException;
import com.Task.phoneContacts.repositories.ContactRepository;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/home")
public class ContactController {

    @RequestMapping(value = "/test")
    @ResponseBody
    public String index() {
        return "Hello World!";
    }

    @Autowired
    private ContactRepository contactRepository;

    //FindALL
    @GetMapping("/contacts")
    List<Contact> findAll() {
        return contactRepository.findAll();
    }

    //FindOne
    @RequestMapping(value = "contact/{id}", method = RequestMethod.GET)
    public Contact findOne(@PathVariable Long id) {
        Optional<Contact> contact = contactRepository.findById(id);
        if (contact.isPresent()) {
            return contact.get();
        } else {
            throw new ContactNotFoundException(id);
        }
    }

    //Save
    @PostMapping("/contacts")
    @ResponseStatus(HttpStatus.CREATED)
    Contact newContact(@RequestBody Contact newContact) {
        return contactRepository.save(newContact);
    }

    // Update or save
    @PutMapping("/contacts/{id}")
    Contact saveOrUpdate(@RequestBody Contact newContact, @PathVariable Long id) {

        return contactRepository.findById(id)
                .map(x -> {
                    x.setName(newContact.getName());
                    x.setContactEmails(newContact.getContactEmails());
                    x.setcontactPhones(newContact.getContactPhones());
                    return contactRepository.save(x);
                })
                .orElseGet(() -> {
                    return contactRepository.save(newContact);
                });
    }
}
