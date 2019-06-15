package com.Task.phoneContacts.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "ContactEmail")
public class ContactEmail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

//    @NotNull(message = "Email may not be null")
//    @NotEmpty
//    @Email
//    @Pattern(regexp = ".+@.+\\..+",
//            message = "Please provide a valid email address")
    private String address;

    @ManyToOne
    @JoinColumn(name = "email_contact_id")
    private Contact contact;

    public ContactEmail() { }

    public ContactEmail(String address, Contact contact) {
        this.address = address;
        this.contact = contact;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        this.Id = id;
    }

    public String getEmail() {
        return address;
    }

    public void setEmail(String address) {
        this.address = address;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return String.format(
                "Phone[id=%d, address='%s']",
                Id, address);
    }
}
