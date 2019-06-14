package com.Task.phoneContacts.repositories;

import com.Task.phoneContacts.entities.Contact;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {

    @Override
    List<Contact> findAll();

    Contact findByName(String name);

    void deleteContactByName(String name);
}
