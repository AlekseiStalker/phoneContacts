package com.Task.phoneContacts.services.serviceImpl;

import com.Task.phoneContacts.entities.Contact;
import com.Task.phoneContacts.repositories.ContactRepository;
import com.Task.phoneContacts.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Contact findByContactName(String name) {
        return contactRepository.findByName(name);
    }

    @Override
    public boolean checkContactExists(String name) {
        if (findByContactName(name) != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean checkEmailExists(String email) {
        return false;
    }

    @Override
    public boolean checkPhoneExists(String phone) {
        return false;
    }

    @Override
    public Contact saveContact(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public void deleteContact(String name) {
        contactRepository.deleteContactByName(name);
    }

    @Override
    public List<Contact> getContactList() {
        return contactRepository.findAll();
    }
}
