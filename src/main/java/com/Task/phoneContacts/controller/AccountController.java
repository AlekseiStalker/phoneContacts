
package com.Task.phoneContacts.controller;
   
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*; 
import com.Task.phoneContacts.service.AccountService;
import com.Task.phoneContacts.dto.*; 

import java.util.*;


@RestController
@RequestMapping(value = "/api/registration")
public class AccountController {
	 
	@Autowired
	private AccountService accountService;
	 
	@RequestMapping("")
    @ResponseBody
    public String welcome() {
        return "Welcome to regestration.";
    } 
     
    @RequestMapping(value = "/createNewAcc", method = RequestMethod.POST)
    public boolean signup(@RequestBody AccountDTO accountDTO) { 
    	return accountService.registerNewAccount(accountDTO);
    } 
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public boolean login(@RequestBody AccountDTO accountDTO) { 
    	return accountService.logIn(accountDTO);
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public boolean logout(@RequestBody AccountDTO accountDTO) { 
    	return accountService.logOut(accountDTO);
    } 
}