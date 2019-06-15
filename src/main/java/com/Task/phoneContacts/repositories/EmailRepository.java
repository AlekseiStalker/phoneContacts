package com.Task.phoneContacts.repositories;

import org.springframework.data.repository.CrudRepository;
import com.Task.phoneContacts.entities.ContactEmail;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailRepository extends CrudRepository<ContactEmail, Long> {

    @Override
    List<ContactEmail> findAll();

    ContactEmail findByAddress(String address);

    void deleteByAddress(String address);
}
