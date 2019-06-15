package com.Task.phoneContacts.services.serviceImpl;

import com.Task.phoneContacts.entities.ContactEmail;
import com.Task.phoneContacts.repositories.EmailRepository;
import com.Task.phoneContacts.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    EmailRepository emailRepository;

    @Override
    public ContactEmail saveEmail(ContactEmail email) {
        return emailRepository.save(email);
    }

    @Override
    public boolean isEmailExists(String address) {
        return false;
    }
}
