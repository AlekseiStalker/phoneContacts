package com.Task.phoneContacts.services;

import com.Task.phoneContacts.entities.Contact;

import java.util.List;

public interface ContactService {

    Contact findByContactName(String name);

    boolean checkContactExists(String name);

    boolean checkEmailExists(String email);

    boolean checkPhoneExists(String phone);

    Contact saveContact(Contact contact);

    void deleteContact(String name);

    List<Contact> getContactList();
}
