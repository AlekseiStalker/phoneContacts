package com.Task.phoneContacts.repositories;

import org.springframework.data.repository.CrudRepository;
import com.Task.phoneContacts.entities.ContactPhone;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneRepository extends CrudRepository<ContactPhone, Long> {

    @Override
    List<ContactPhone> findAll();

    //ContactPhone findByName(String phoneName);

    //void deletePhoneByName(String name);
}

