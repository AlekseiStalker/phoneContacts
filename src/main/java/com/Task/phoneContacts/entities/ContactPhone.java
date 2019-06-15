//package com.Task.phoneContacts.entities;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Pattern;
//import javax.validation.constraints.Size;
//
//@Entity
//@Table(name = "ContactPhone")
//public class ContactPhone {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private long Id;
//
//    @NotNull(message = "Phone may not be null")
//    @NotEmpty
//    @Size(min=10, max=13)
//    @Pattern(regexp = "\\d{3}-?\\d{3}-?\\d{2}-?\\d{2}|" +
//            "[+\\d{1,3}]?\\d{3}-?\\d{3}-?\\d{2}-?\\d{2}",
//            message = "Please provide a valid phone number")
//    private String number;
//
//    @ManyToOne
//    @JoinColumn(name = "contact_id")
//    private Contact contact;
//
//    public ContactPhone() {}
//
//    public ContactPhone(String phone) {
//        this.number = phone;
//    }
//
//    public long getId() {
//        return Id;
//    }
//
//    public void setId(long id) {
//        this.Id = id;
//    }
//
//    public String getPhone() {
//        return number;
//    }
//
//    public void setPhone(String phone) {
//        this.number = phone;
//    }
//
//    public Contact getContact() {
//        return contact;
//    }
//
//    public void setContact(Contact phoneContact) {
//        this.contact = phoneContact;
//    }
//
//    @Override
//    public String toString() {
//        return String.format(
//                "Phone[id=%d, phone='%s']",
//                Id, number);
//    }
//}