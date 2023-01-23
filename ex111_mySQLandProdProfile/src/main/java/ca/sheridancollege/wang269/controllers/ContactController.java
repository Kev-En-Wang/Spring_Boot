package ca.sheridancollege.wang269.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.wang269.beans.Contact;
import ca.sheridancollege.wang269.database.DatabaseAccess;

@Controller
public class ContactController{
	
	@Autowired
	DatabaseAccess da;
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("contact", new Contact());
		model.addAttribute("contactList", da.findAll());	
		return "index";
	}
	
	@PostMapping("/insertContact")
	public String insertContact(Model model, @ModelAttribute Contact contact) {
		
		da.save(contact.getFirstName(), contact.getLastName(), contact.getPhoneNumber(), contact.getBirthday());
		
		model.addAttribute("contact", new Contact());
		model.addAttribute("contactList", da.findAll());
		
		return "index";
	}
}