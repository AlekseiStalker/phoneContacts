package com.Task.phoneContacts.service; 

import com.Task.phoneContacts.dto.AccountDTO; 

public interface AccountService {
	 
	boolean registerNewAccount(AccountDTO accountDto);

	boolean logIn(AccountDTO accountDTO);  
	
	boolean logOut(AccountDTO accountDTO);  
}