package com.Task.phoneContacts.controllers;

import com.Task.phoneContacts.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/home2")
public class TestController {

    @Autowired
    private EmailService emailService;

    @RequestMapping(method = RequestMethod.GET)
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


    @GetMapping
    public String SaveEmail(@RequestParam (name = "name", required = false, defaultValue = "def@com.ua") String name) {

        return emailService.saveEmail();
    }
}
