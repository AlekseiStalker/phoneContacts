package com.Task.phoneContacts.services.serviceImpl;

import com.Task.phoneContacts.entities.ContactEmail;
import com.Task.phoneContacts.repositories.EmailRepository;
import com.Task.phoneContacts.services.EmailService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    EmailRepository emailRepository;

    @Override
    public ContactEmail createEmail(ContactEmail email) {
        return emailRepository.save(email);
    }
 
	@Override
	public List<ContactEmail> findAll() { 
		return emailRepository.findAll();
	}

	@Override
	public ContactEmail findContactEmail(Long id) { 
		Optional<ContactEmail> contactEmail = emailRepository.findById(id);
		return contactEmail.get();
	}
	 
    @Override
    public boolean isEmailExists(String address) { 
        return emailRepository.findByAddress(address) != null;
    }
}
