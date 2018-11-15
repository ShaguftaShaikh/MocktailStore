package com.api.mocktailstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.mocktailstore.entities.User;
import com.api.mocktailstore.repository.UserRepository;

@Service("visitorService")
public class UserService {

	private UserRepository visitorRepository;

	@Autowired
	public UserService(UserRepository visitorRepository) {
		this.visitorRepository = visitorRepository;
	}

	public void saveVisitor(User user) {
		visitorRepository.save(user);
	}

	/*public Customer findByUsername(String username) {
		return visitorRepository.findByUsername(username);
	}*/
}
