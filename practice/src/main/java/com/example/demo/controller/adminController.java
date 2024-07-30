package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.admins;
import com.example.demo.form.UserRegistrationForm;
import com.example.demo.repository.AdminRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller

public class adminController {
	
	   @RequestMapping(value="/admin/signin")
	    public String signin() {
	  	  return "/admin/signin";
	    }
	   
	 
	   @Autowired
       private AdminRepository adminRepository;
	   
	    @GetMapping("/admin/signup")
	    public String signup(Model model) {
	    	model.addAttribute("userRegistrationForm", new UserRegistrationForm ());
	    	return "signup";
	    }
	    
	    @PostMapping("/admin/signup")
	    public String signup(@Validated @ModelAttribute("userRegistrationForm") UserRegistrationForm userRegistrationForm, BindingResult errorResult, HttpServletRequest request) {

	        if (errorResult.hasErrors()) {
	          return "signup";
	        }

	        HttpSession session = request.getSession();
	        session.setAttribute("userRegistrationForm", userRegistrationForm);

	        return "redirect:/admin/signup/confirm2";
	    }
	    @GetMapping("/admin/signup/confirm2")
	    public String confirm(Model model, HttpServletRequest request) {
	        HttpSession session = request.getSession();

	        UserRegistrationForm userRegistrationForm = (UserRegistrationForm) session.getAttribute("userRegistrationForm");
	        model.addAttribute("userRegistrationForm", userRegistrationForm);
	        return "confirmation2";
	    }
	    
	    @Autowired 
	    private PasswordEncoder passwordEncoder;
	    
	    @PostMapping("/admin/signup/register")
	    public String register(Model model, HttpServletRequest request) {
	    
	    	HttpSession session = request.getSession();
	    	UserRegistrationForm userRegistrationForm = (UserRegistrationForm) session.getAttribute("userRegistrationForm");

	        admins admins = new admins();
	        admins.setLastName(userRegistrationForm.getLastName());
	        admins.setFirstName(userRegistrationForm.getFirstName());
	        admins.setEmail(userRegistrationForm.getEmail());
	        admins.setPassword(passwordEncoder.encode(userRegistrationForm.getPassword()));

	        adminRepository.save(admins);

	        return "redirect:/admin/signup/complete2";
	    }
	    
	    @GetMapping("/admin/signup/complete2")
	    public String complete(Model model, HttpServletRequest request) {

	        if (request.getSession(false) == null) {
	          return "redirect:/admin/signup";
	        }
	    
	    HttpSession session = request.getSession();
	    UserRegistrationForm userRegistrationForm = (UserRegistrationForm) session.getAttribute("userRegistrationForm");
	    model.addAttribute("userRegistrationForm", userRegistrationForm);
	    
	    session.invalidate();
	    
	    return "complete2";
	    }
}
