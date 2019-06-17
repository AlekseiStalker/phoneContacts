package com.Task.phoneContacts.service.impl;
 
import com.Task.phoneContacts.model.*;
import com.Task.phoneContacts.dto.ContactDTO;
import com.Task.phoneContacts.dto.AccountDTO;
import com.Task.phoneContacts.error.*;
import com.Task.phoneContacts.repository.ContactRepository;
import com.Task.phoneContacts.repository.AccountRepository;
import com.Task.phoneContacts.service.ContactService;
import com.Task.phoneContacts.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;
 
import java.util.*;

@Service
public class UserServiceImpl implements AccountService {
	
	private final InMemoryUserDetailsManager inMemoryUserDetailsManager;
	
	@Autowired
    public UserServiceImpl(InMemoryUserDetailsManager inMemoryUserDetailsManager) {
       this.inMemoryUserDetailsManager = inMemoryUserDetailsManager;
    }
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
	 
	@Override
	public boolean registerNewAccount(AccountDTO accountDto) {
	    if (isLoginExist(accountDto.getLogin())) {
	        throw new UserAlreadyExistsException(accountDto.getLogin());
	    }
	    
	    if(!isStringEquals(accountDto.getPassword(), accountDto.getMatchingPassword())) {
	    	throw new PasswordMatchException();
	    }
	     
	    String accountLogin = accountDto.getLogin();
	    String encrytedPassword = this.passwordEncoder().encode(accountDto.getPassword()); 
    	
    	Account account = new Account();
    	account.setLogin(accountLogin); 
    	account.setPassword(encrytedPassword); 
	    accountRepository.save(account); 
	    
	    saveAccountToLocalStorage(accountLogin, encrytedPassword);
	    
	    return true;
	}
	
	public boolean logIn(AccountDTO accountDto) {
		Account account = accountRepository.findByLogin(accountDto.getLogin());
		
		if(account != null) {
			String accountLogin = accountDto.getLogin();
		    String encrytedPassword = this.passwordEncoder().encode(accountDto.getPassword()); 
		    
		    boolean success = saveAccountToLocalStorage(accountLogin, encrytedPassword);
		    return success;
		} 
		return false;
	}
	
	public boolean logOut(AccountDTO accountDto) {
		Account account = accountRepository.findByLogin(accountDto.getLogin());
		
		if(account != null) {
			String accountLogin = accountDto.getLogin(); 
			boolean success = removeAccountFromLocalStorage(accountLogin);
			return success;
		} else {
			return false;
		}
	}
	 
	private boolean isLoginExist(String userlogin) {
		return accountRepository.findByLogin(userlogin) != null;
	} 
	
	private boolean saveAccountToLocalStorage(String accountLogin, String encrytedPassword) {
		UserDetails currentUser = User.withUsername(accountLogin).password(encrytedPassword).roles("USER").build();
	    inMemoryUserDetailsManager.createUser(currentUser);
	    return true;
	}
	
	private boolean removeAccountFromLocalStorage(String accountLogin) {
		if( inMemoryUserDetailsManager.userExists(accountLogin)) {
			inMemoryUserDetailsManager.deleteUser(accountLogin); 
			return true;
		} else {
			return false;
		}
	}
	
	private boolean isStringEquals(String s1, String s2) {
		if(s1.length() != s2.length()) {
			return false;
		}
		for(int i = 0; i < s1.length(); i++) {
			int s1_ch = (int)s1.charAt(i);
			int s2_ch = (int)s2.charAt(i);
			
			if(s1_ch != s2_ch) {
				return false;
			}
		}
		return true;
	}
}
