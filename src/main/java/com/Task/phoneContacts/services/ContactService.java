package com.Task.phoneContacts.services;

import com.Task.phoneContacts.entities.Contact;
import com.Task.phoneContacts.entities.ContactEmail; 

import java.util.List;
import java.util.Set;

public interface ContactService {

    Contact findByContactName(String name);
    
    Contact createContact(Contact contact, Set<ContactEmail> contactEmails);
    	 
    boolean checkContactExists(String name);

    boolean checkEmailExistsForContact(Contact contact, String email);

    //boolean checkPhoneExists(String phone);

    Contact saveContact(Contact contact);

    void deleteContact(String name);

    List<Contact> getContactList();
}
