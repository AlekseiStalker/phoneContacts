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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long emailId;

    @NotNull(message = "Email may not be null")
    @NotEmpty
    @Email
    @Pattern(regexp = ".+@.+\\..+",
            message = "Please provide a valid email address")
    private String email;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    private Contact contact;

    public ContactEmail() { }

    public ContactEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return emailId;
    }

    public void setId(long id) {
        this.emailId = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
                "Phone[id=%d, email='%s']",
                emailId, email);
    }
}
