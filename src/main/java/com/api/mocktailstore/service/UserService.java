package com.api.mocktailstore.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.mocktailstore.entities.User;
import com.api.mocktailstore.repository.UserRepository;

@Service("userService")
public class UserService {

	private UserRepository userRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void saveUser(User user) {
		userRepository.save(user);
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public boolean login(final User savedUser, final User user) {
		LOGGER.debug("Login request: savedUser[email: " + savedUser.getEmail() + " password:" + savedUser.getPassword()
				+ "], requestedUser[email:" + user.getEmail() + " password:" + user.getPassword() + "]");
		if (savedUser.getEmail().equals(user.getEmail()) && savedUser.getPassword().equals(user.getPassword())) {
			return true;
		}
		return false;
	}

	/**
	 * Updates the user profile except id and email
	 * 
	 * @param user
	 * @return
	 */
	public User editProfile(final User user) {
		User savedUser = userRepository.findByEmail(user.getEmail());
		LOGGER.debug("Updating " + savedUser.toString());
		savedUser.setId(savedUser.getId());
		savedUser.setContactNo(user.getContactNo());
		savedUser.setFirstName(user.getFirstName());
		savedUser.setLastName(user.getLastName());
		savedUser.setPassword(user.getPassword());
		userRepository.save(savedUser);
		LOGGER.debug("Updated to: " + savedUser.toString());
		return savedUser;
	}
}
