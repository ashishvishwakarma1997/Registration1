package com.marketingapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marketingapp.Entity.Registration;

public interface RegistrationRepository extends JpaRepository<Registration, Long>{

}
