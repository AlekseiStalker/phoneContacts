package com.Task.phoneContacts.repository;

import org.springframework.data.repository.CrudRepository;

import com.Task.phoneContacts.model.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {
	
    Account findByLogin(String name); 
}
