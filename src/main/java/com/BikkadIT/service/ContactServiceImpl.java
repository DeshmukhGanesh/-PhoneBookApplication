package com.BikkadIT.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BikkadIT.model.Contact;
import com.BikkadIT.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactServiceI{

	@Autowired
	private ContactRepository contactRepository;
	
	@Override
	public boolean saveContact(Contact contact) {
		Contact save = contactRepository.save(contact);
		if(save!=null) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<Contact> getAllContacts() {
		List<Contact> contacts = contactRepository.findAll();
		/*We only get active switch Y contacts by following method of stream api*/
		Stream<Contact> stream = contacts.stream();
		Stream<Contact> filter = stream.filter(contact->contact.getActiveSwitch()=='Y');
		List<Contact> collect = filter.collect(Collectors.toList());
		
		return collect;
	}

	@Override
	public Contact getContactById(Integer cid) {
		Contact findById = contactRepository.findById(cid).get();
		return findById;
	}

	@Override
	public boolean updateContact(Contact contact) {
		Contact save = contactRepository.save(contact);
		if(save==null) {
			return false;	
		}else {
			return true;
		}
	}

	@Override
	public boolean deleteById(Integer cid) {
/* folling three are hard delete methods		
 *     1)
//		boolean existsById = contactRepository.existsById(cid);
//		if(existsById) {
//			contactRepository.deleteById(cid);
//			return true;
//		}else {
//			return false;
//		}
		
       2)		
//		Contact contact = contactRepository.findById(cid).get();
//		if(contact!=null) {
//			contactRepository.deleteById(cid);
//			return true;
//		}else {
//			return false;
//		}
		
		3)
//		Optional<Contact> findById = contactRepository.findById(cid);
//		if(findById.isPresent()) {
//			contactRepository.deleteById(cid);
//			return true;
//		}else {
//			return false;
//		}*/
		
		/*following is the soft delete method*/
		
		Optional<Contact> contact = contactRepository.findById(cid);
		if(contact.isPresent()) {
			Contact contact2 = contact.get();
			contact2.setActiveSwitch('N');
			contactRepository.save(contact2);
			return true;
		}else {
			return false;
		}
		
	}

}
