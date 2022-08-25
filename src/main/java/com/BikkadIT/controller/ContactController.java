package com.BikkadIT.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.BikkadIT.model.Contact;
import com.BikkadIT.service.ContactServiceI;

@RestController
public class ContactController {

	@Autowired
	private ContactServiceI contactServiceI;
	
	
	@PostMapping(value = "/saveContact",consumes = "application/json")
	public ResponseEntity<String> saveContact(@RequestBody Contact contact){
		
		boolean saveContact = contactServiceI.saveContact(contact);
		if(saveContact==true) {
			String msg="Contact Saved Successfully";
			return new ResponseEntity<String>(msg,HttpStatus.OK);
		}else {
			String msg1="Contact not Saved";
			return new ResponseEntity<String>(msg1,HttpStatus.BAD_REQUEST);	
		}		
	}
	
	@GetMapping(value = "/getAllContact",produces = "application/json")
	public ResponseEntity<List<Contact>> getAllContact(){
		List<Contact> allContacts = contactServiceI.getAllContacts();
		if(allContacts != null) {
			return new ResponseEntity<List<Contact>>(allContacts,HttpStatus.OK);
		}else {
			String msg="Data not found";
			return new ResponseEntity(msg,HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/getContactById/{cid}",produces = "application/json")
	public ResponseEntity<Contact> getContactById(@PathVariable Integer cid){
		Contact contactById = contactServiceI.getContactById(cid);
		return new ResponseEntity<Contact>(contactById,HttpStatus.OK);
	}
	
	
}
