package com.Task.phoneContacts.repositories;

import org.springframework.data.repository.CrudRepository;
import com.Task.phoneContacts.entities.ContactPhone;

import java.util.List;

public interface PhoneRepository extends CrudRepository<ContactPhone, Long> {

    List<ContactPhone> findAll();
}

