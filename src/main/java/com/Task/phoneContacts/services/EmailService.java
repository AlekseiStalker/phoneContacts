package com.Task.phoneContacts.services;

import com.Task.phoneContacts.entities.ContactEmail;

public interface EmailService {

    ContactEmail saveEmail(ContactEmail email);

    boolean isEmailExists(String email);
}
