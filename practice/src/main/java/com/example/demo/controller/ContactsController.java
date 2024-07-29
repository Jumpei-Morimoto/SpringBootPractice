package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Contact;
import com.example.demo.entity.ContactUpdateRequest;
import com.example.demo.service.ContactsService;

@Controller
public class ContactsController {

	@Autowired
	  private ContactsService contactsService;
	
	@GetMapping("/admin/contacts")
	public String displayList(Model model) {
		List<Contact> contactlist = contactsService.searchAll();
		model.addAttribute("contactlist",contactlist);
		return "contacts";
	}
	
	@GetMapping("/admin/contacts/{id}")
	public String displayView(@PathVariable Long id, Model model) {
		Contact contact2 = contactsService.findById(id);
		model.addAttribute("contactData", contact2);
		return "detail";
	}
	
	@GetMapping("/admin/contacts/{id}/edit")
	public String displayEdit(@PathVariable Long id, Model model) {
		Contact contact3 =contactsService.findById(id);
		ContactUpdateRequest contactUpdateRequest = new ContactUpdateRequest();
		contactUpdateRequest.setId(contact3.getId());
		contactUpdateRequest.setLastName(contact3.getLastName());
		contactUpdateRequest.setFirstName(contact3.getFirstName());
		contactUpdateRequest.setEmail(contact3.getEmail());
		contactUpdateRequest.setPhone(contact3.getPhone());
		contactUpdateRequest.setZipCode(contact3.getZipCode());
		contactUpdateRequest.setAddress(contact3.getAddress());
		contactUpdateRequest.setBuildingName(contact3.getBuildingName());
		contactUpdateRequest.setBody(contact3.getBody());
		model.addAttribute("contactUpdateRequest", contactUpdateRequest);
		return "edit";
		
	}

	
	@PostMapping("/admin/update")
	public String update(@Validated @ModelAttribute ContactUpdateRequest contactUpdateRequest, BindingResult result, Model model) {
		
		if (result.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
			
			for (ObjectError error : result.getAllErrors()) {
			errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("validationError",errorList);
			return "edit";
		}
		
		contactsService.update(contactUpdateRequest);
		return String.format("redirect:/admin/contacts/%d", contactUpdateRequest.getId());
	}

	@GetMapping("/admin/{id}/delete")
	  public String delete(@PathVariable Long id) {
		contactsService.deleteContact(id);
	    return "redirect:/admin/contacts";
	  }
}