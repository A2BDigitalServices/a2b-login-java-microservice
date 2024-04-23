package com.todo.backend.services;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.todo.backend.entities.User;
import com.todo.backend.entities.UserDto;
import com.todo.backend.repo.UserRepo;


@Service
public class UserSingUpServiceImpl {
	@Autowired
	private UserRepo repo;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public UserDto createUser(UserDto userDetails) {
		userDetails.setUserId(UUID.randomUUID().toString());
		userDetails.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		User userEntity = mapper.map(userDetails, User.class);
		userEntity.setRole("customer");
		repo.save(userEntity);
		UserDto userDto = mapper.map(userEntity, UserDto.class);
		return userDto;
	}
	
	public String getRoleByUserName(String username) {
		User user = repo.findByEmail(username);
		return user.getRole();
	}
	
	public String getFirstNameByUsername(String username) {
		User user = repo.findByEmail(username);
		return user != null? user.getFirstname(): "";
	}
}
