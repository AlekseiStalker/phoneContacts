package com.Task.phoneContacts.controllers;

import com.Task.phoneContacts.entities.ContactEmail;
//import com.Task.phoneContacts.entities.ContactPhone;
import com.Task.phoneContacts.repositories.EmailRepository;
//import com.Task.phoneContacts.repositories.PhoneRepository;
import com.Task.phoneContacts.services.EmailService;

import org.springframework.http.MediaType;
import java.util.List;
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
    
 
    @RequestMapping(value = "/requestEmail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ContactEmail SaveEmail(@RequestBody ContactEmail address) {
    	return emailRepository.save(address); 
    }
     
    @RequestMapping(value = "/countEnmail", method = RequestMethod.GET)
    @ResponseBody
    public List<ContactEmail> CountEmails() {
    	return emailRepository.findAll();
    }
     
//  @Autowired
//  PhoneRepository phoneRepository;
    
//    @RequestMapping(value = "/requestPhone", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public ContactPhone SavePhone(@RequestBody ContactPhone number) {
//    	return phoneRepository.save(number); 
//    }
    
//    @RequestMapping(value = "/countPhone", method = RequestMethod.GET)
//    @ResponseBody
//    public List<ContactPhone> CountPhone() {
//    	return phoneRepository.findAll();
//    }
}
