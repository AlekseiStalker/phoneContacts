package com.Task.phoneContacts.errors;

public class ContactAlreadyExistsException extends RuntimeException {

    public ContactAlreadyExistsException(String name) {
        super("Contact id already exists : " + name);
    }

}