package com.Task.phoneContacts.repositories;

import org.springframework.data.repository.CrudRepository;
import com.Task.phoneContacts.entities.ContactEmail;

import java.util.List;

public interface EmailRepository extends CrudRepository<ContactEmail, Long> {

    List<ContactEmail> findAll();
}
