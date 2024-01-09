package com.marketingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.marketingapp.Dto.RegistrationDto;
import com.marketingapp.Entity.Registration;
import com.marketingapp.service.RegistrationService;
import com.marketingapp.utill.EmailService;

@Controller
public class Registrationcontroller {

	@Autowired
	private RegistrationService registrationService;
	
	@Autowired
	private EmailService emailService;

	// http://localhost:8080/view

	// handler methods

	@RequestMapping("/view")
	public String viewRegistrationpage() {

		return "create_lead";
	}

//	@RequestMapping("/saveReg")
//	public String saveRegistration(@ModelAttribute Registration registration) {
//		registrationService.saveRegistration(registration);
//
//		return "create_lead";
//	}

	/*
	 * @RequestMapping("/saveReg") public String
	 * saveRegistration(@RequestParam("firstName") String firstName,
	 * 
	 * @RequestParam("lastName") String lastName, @RequestParam("email") String
	 * email,
	 * 
	 * @RequestParam("mobile") long mobile
	 * 
	 * ) {
	 * 
	 * Registration reg = new Registration(); reg.setFirstName(firstName);
	 * reg.setLastName(lastName); reg.setEmail(email); reg.setMobile(mobile);
	 * 
	 * registrationService.saveRegistration(reg);
	 * 
	 * 
	 * return "create_lead";
	 * 
	 * }
	 */

	@RequestMapping("/saveReg")
	public String saveRegistration(RegistrationDto dto, Model model) {

		Registration reg = new Registration();
		reg.setFirstName(dto.getFirstName());
		reg.setLastName(dto.getLastName());
		reg.setEmail(dto.getEmail());
		reg.setMobile(dto.getMobile());

		registrationService.saveRegistration(reg);
		
        emailService.sendEmail(dto.getEmail(), "welcome", "apptest");
		model.addAttribute("msg", "Record is saved ");

		return "create_lead";
	}

	@RequestMapping("/getAllReg")
	public String getAllRegistrations(Model model) {
		List<Registration> allRegistration = registrationService.getAllRegistration();
		model.addAttribute("resgistrations", allRegistration);
		return "list_registrations";

	}

	@RequestMapping("/delete")
	public String deleteLead(@RequestParam long id, Model model) {
		registrationService.deleteLead(id);
		List<Registration> allRegistration = registrationService.getAllRegistration();
		model.addAttribute("resgistrations", allRegistration);
		return "list_registrations";

	}

	@RequestMapping("/update")
	public String viewupdatepage(@RequestParam long id, ModelMap model) {
		Registration registration = registrationService.getRegistrationByID(id);
		model.addAttribute("registration", registration);
		return "update_lead";

	}

	@RequestMapping("/updateReg")
	public String updateRegistration(RegistrationDto dto, Model model) {

		Registration reg = new Registration();
		reg.setId(dto.getId());
		reg.setFirstName(dto.getFirstName());
		reg.setLastName(dto.getLastName());
		reg.setEmail(dto.getEmail());
		reg.setMobile(dto.getMobile());

		registrationService.saveupdateRegistration(reg);

		List<Registration> allRegistration = registrationService.getAllRegistration();
		model.addAttribute("resgistrations", allRegistration);
		return "list_registrations";

	}
}
