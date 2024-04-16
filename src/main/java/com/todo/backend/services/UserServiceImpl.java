package com.todo.backend.services;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.todo.backend.entities.User;
import com.todo.backend.exceptions.InvalidCredentialsException;
import com.todo.backend.repo.UserRepo;



@Service
public class UserServiceImpl implements UserDetailsService{
	
	@Value("${userDetails.errorMessage}")
	private String USER_DOES_NOT_EXIST_MESSAGE;

//	@Autowired
//	private UserRepository userRepository;
	
	@Autowired
	private UserRepo userRepo;

	/**
	 * Gets the user based on the user-name if it exists in the database
	 * 
	 * @param username
	 * @return Optional<User> Object
	 */
	public Optional<User> findByUsername(String username) {
		return userRepo.findById(username);
	}

	/**
	 * Loads user from the database if it exists.
	 * After loading the details, compares the given username from username in the DB.
	 * 
	 * @param username
	 * @return UserDetails
	 */
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		Optional<User> userOptional = findByUsername(username);
		if (!userOptional.isPresent()) {
			throw new InvalidCredentialsException(USER_DOES_NOT_EXIST_MESSAGE);
		} else {
			//log.info("Username: {} is valid", username);
			User user = userOptional.get();
			return new org.springframework.security.core.userdetails.User(username,user.getEncryptedPassword(),
					Collections.singletonList(new SimpleGrantedAuthority(user.getUserId())));
		}
	}

	
}
