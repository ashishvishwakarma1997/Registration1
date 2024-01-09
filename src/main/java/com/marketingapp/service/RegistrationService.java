package com.marketingapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.marketingapp.Entity.Registration;
import com.marketingapp.Repository.RegistrationRepository;

@Service
public class RegistrationService {

	@Autowired
	private RegistrationRepository registrationRepo;

	public void saveRegistration(Registration registration) {

		registrationRepo.save(registration);

	}

//	 public List <Registration> getAllRegistrations() {
//	 List<Registration> registrations = registrationRepo.findAll();
//	 return registrations;
//
//	 }

	public List<Registration> getAllRegistration() {
		List<Registration> findAll = registrationRepo.findAll();
		return findAll;
	}

	public void deleteLead(long id) {
		registrationRepo.deleteById(id);
 
	}

	public Registration getRegistrationByID(long id) {
		Optional<Registration> findById = registrationRepo.findById(id);
		return findById.get();
	}

	public void saveupdateRegistration(Registration reg) {
		registrationRepo.save(reg);
		
	}

}
