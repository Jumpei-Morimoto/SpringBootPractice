package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Contact;
import com.example.demo.entity.ContactUpdateRequest;
import com.example.demo.repository.ContactRepository;

@Service
@Transactional(rollbackFor = Exception.class)
public class ContactsService {

	@Autowired
	private ContactRepository contactRepository2;
	
	public List<Contact> searchAll(){
		return contactRepository2.findAll();
	}
	
	public Contact findById(long id) {
		return contactRepository2.findById(id).get();
	}
	
	public void update(ContactUpdateRequest contactUpdateRequest) {
		Contact contact4 = findById(contactUpdateRequest.getId());
		contact4.setLastName(contactUpdateRequest.getLastName());
		contact4.setFirstName(contactUpdateRequest.getFirstName());
		contact4.setEmail(contactUpdateRequest.getEmail());
		contact4.setPhone(contactUpdateRequest.getPhone());
		contact4.setZipCode(contactUpdateRequest.getZipCode());
		contact4.setAddress(contactUpdateRequest.getAddress());
		contact4.setBuildingName(contactUpdateRequest.getBuildingName());
		contact4.setContactType(contactUpdateRequest.getContactType());
		contact4.setBody(contactUpdateRequest.getBody());
		contact4.setUpdatedAt(new Date());
		contactRepository2.save(contact4);
		
	}
	 public void deleteContact(Long id) {
	        contactRepository2.deleteById(id);
	    }
}
