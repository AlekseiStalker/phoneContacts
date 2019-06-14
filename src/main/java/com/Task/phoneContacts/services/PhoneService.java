package com.Task.phoneContacts.services;

import com.Task.phoneContacts.entities.ContactEmail;
import com.Task.phoneContacts.entities.ContactPhone;

public interface PhoneService {

    ContactPhone savePhone(ContactPhone phone);

    boolean isPhoneExists(String phone);
}
