package com.Task.phoneContacts.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Name may not be null")
    @Size(min=3, max=25)
    private String name;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ContactEmail> contactEmails;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ContactPhone> contactPhones;

    public Contact() {}

    public Contact(String name) {
        this.name = name;

        contactEmails = new HashSet<>();
        contactPhones = new HashSet<>();
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setContactEmails(Set<ContactEmail> contactEmails) {
        this.contactEmails = contactEmails;
    }
    public Set<ContactEmail> getContactEmails() {
        return contactEmails;
    }

    public void setcontactPhones(Set<ContactPhone> contactPhones) {
        this.contactPhones = contactPhones;
    }
    public Set<ContactPhone> getContactPhones() {
        return contactPhones;
    }
}