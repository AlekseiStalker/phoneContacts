package com.Task.phoneContacts.services.serviceImpl;

import com.Task.phoneContacts.entities.ContactPhone;
import com.Task.phoneContacts.repositories.PhoneRepository;
import com.Task.phoneContacts.services.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    PhoneRepository emailRepository;

    @Override
    public ContactPhone savePhone(ContactPhone phone) {
        return emailRepository.save(phone);
    }

    @Override
    public boolean isPhoneExists(String phone) {
        return false;
    }
}
