package com.BikkadIT.service;

import java.util.List;

import com.BikkadIT.model.Contact;

public interface ContactServiceI {

	boolean saveContact(Contact contact);
	
	List<Contact> getAllContacts();
	
	Contact getContactById(Integer cid);
}
