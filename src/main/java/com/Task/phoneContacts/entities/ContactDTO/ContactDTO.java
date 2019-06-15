package com.Task.phoneContacts.entities.ContactDTO;

public class ContactDTO {
	
	private String name;
	
	private String emails[];
	
	public ContactDTO() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getEmails() {
		return emails;
	}

	public void setEmails(String emails[]) {
		this.emails = emails;
	} 
	
	
}