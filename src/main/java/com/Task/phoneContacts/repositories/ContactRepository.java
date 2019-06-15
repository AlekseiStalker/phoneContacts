package com.Task.phoneContacts.repositories;

import com.Task.phoneContacts.entities.Contact;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {

    @Override
    List<Contact> findAll();

    Optional<Contact> findById(Long id);
    
    Contact findByName(String name);

    void deleteContactByName(String name);
}
