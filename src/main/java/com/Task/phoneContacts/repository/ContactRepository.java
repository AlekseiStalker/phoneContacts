package com.Task.phoneContacts.repository;

import com.Task.phoneContacts.model.Contact;
import java.util.List; 

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {

    @Override
    List<Contact> findAll();
 
    Contact findByName(String name); 
}
