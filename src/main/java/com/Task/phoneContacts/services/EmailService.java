package com.Task.phoneContacts.services;

import java.util.List;

import com.Task.phoneContacts.entities.ContactEmail;

public interface EmailService {

    ContactEmail createEmail(ContactEmail email);
    
    List<ContactEmail> findAll();
    
    ContactEmail findContactEmail(Long id);

    boolean isEmailExists(String address);
}
