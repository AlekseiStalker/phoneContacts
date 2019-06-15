package com.Task.phoneContacts.controllers;

import com.Task.phoneContacts.entities.ContactEmail;
import com.Task.phoneContacts.repositories.EmailRepository;
import com.Task.phoneContacts.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/home")
public class TestController {

    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "/checkGet", method = RequestMethod.GET)
    String get(){
        return "Hello from get";
    }
    @RequestMapping(method = RequestMethod.DELETE)
    String delete(){
        return "Hello from delete";
    }
    @RequestMapping(method = RequestMethod.POST)
    String post(){
        return "Hello from post";
    }
    @RequestMapping(method = RequestMethod.PUT)
    String put(){
        return "Hello from put";
    }
    @RequestMapping(method = RequestMethod.PATCH)
    String patch(){
        return "Hello from patch";
    }

    @Autowired
    EmailRepository emailRepository;

    @PostMapping("/request") 
    public ResponseEntity<ContactEmail> SaveEmail(@RequestBody ContactEmail email) {
    	emailRepository.save(email);
        return ResponseEntity.ok().body(email);
    }
    
    @RequestMapping(value = "/checkEmail", method = RequestMethod.GET)
    public String CountEmails() {
    	return emailRepository.findAll().size() > 0 ? "yes" : "no";
    }
}
