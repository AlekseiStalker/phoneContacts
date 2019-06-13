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
@RequestMapping
public class ContactController {

    @RequestMapping(value = "/")
    @ResponseBody
    public String index() {
        return "Hello World!";
    }

    @Autowired
    private ContactRepository contactRepository;

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

    //FindALL
    @GetMapping("/contacts")
    List<Contact> findAll() {
        return contactRepository.findAll();
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

    /*// Update phone only
    @PatchMapping("/books/{id}")
    Contact patch(@RequestBody Map<String, String> update, @PathVariable Long id) {

        return contactRepository.findById(id)
                .map(x -> {

                    String author = update.get("author");
                    if (!StringUtils.isEmpty(author)) {
                        x.setAuthor(author);

                        // better create a custom method to update a value = :newValue where id = :id
                        return repository.save(x);
                    } else {
                        throw new BookUnSupportedFieldPatchException(update.keySet());
                    }

                })
                .orElseGet(() -> {
                    throw new ContactNotFoundException(id);
                });

    }

    @DeleteMapping("/contacts/{id}")
    void deleteContact(@PathVariable Long id) {
        contactRepository.deleteById(id);
    }*/

    /////////////////////

    /*@PostMapping("/addcontact")
    public String addContact(@Valid Contact contact, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-contact";
        }

        contactRepository.save(contact);
        model.addAttribute("contacts", contactRepository.findAll());
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Contact contact = contactRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid contact Id:" + id));
        model.addAttribute("contact", contact);
        return "update-contact";
    }

    @PostMapping("/update/{id}")
    public String updateContact(@PathVariable("id") long id, @Valid Contact contact, BindingResult result, Model model) {
        if (result.hasErrors()) {
            contact.setId(id);
            return "update-contact";
        }

        contactRepository.save(contact);
        model.addAttribute("contacts", contactRepository.findAll());
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String deleteContact(@PathVariable("id") long id, Model model) {
        Contact contact = contactRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid contact Id:" + id));
        contactRepository.delete(contact);
        model.addAttribute("contacts", contactRepository.findAll());
        return "index";
    }*/
}

