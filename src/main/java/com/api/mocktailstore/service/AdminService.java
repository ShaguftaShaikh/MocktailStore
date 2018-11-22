package com.api.mocktailstore.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.mocktailstore.entities.Admin;
import com.api.mocktailstore.repository.AdminRepository;

@Service("adminService")
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminService.class);
	
	public Admin findByEmail(String email) {
		return adminRepository.findByEmail(email);
	}
	
	public boolean login(final Admin savedUser, final Admin admin) {
		LOGGER.debug("Login request: savedUser[email: " + savedUser.getEmail() + " password:" + savedUser.getPassword()
				+ "], requestedUser[email:" + admin.getEmail() + " password:" + admin.getPassword() + "]");
		if (savedUser.getEmail().equals(admin.getEmail()) && savedUser.getPassword().equals(admin.getPassword())) {
			return true;
		}
		return false;
	}
}
