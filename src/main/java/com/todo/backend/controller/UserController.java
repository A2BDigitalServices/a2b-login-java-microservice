package com.todo.backend.controller;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.todo.backend.entities.AuthenticationBean;
import com.todo.backend.entities.DeletedMsg;
import com.todo.backend.entities.LoginResponse;
import com.todo.backend.entities.Todo;
import com.todo.backend.entities.UserDto;
import com.todo.backend.entities.UserRequest;
import com.todo.backend.entities.UserResponse;
import com.todo.backend.entities.UserSignUpRequest;
import com.todo.backend.exceptions.InvalidCredentialsException;
import com.todo.backend.services.TodoService;
import com.todo.backend.services.UserSingUpServiceImpl;
import com.todo.backend.util.JwtUtil;

@CrossOrigin(origins = "https://a2bdigital.000webhostapp.com")
//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@Slf4j
public class UserController {
	
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserDetailsService userService;

	@Value("${userDetails.badCredentialsMessage}")
	private String BAD_CREDENTIALS_MESSAGE;

	@Value("${userDetails.disabledAccountMessage}")
	private String DISABLED_ACCOUNT_MESSAGE;
	
	@Value("${userDetails.lockedAccountMessage}")
	private String LOCKED_ACCOUNT_MESSAGE;
	
	@Autowired
	private TodoService service;
	
	@Autowired
	private UserSingUpServiceImpl service1;
	
	
	@PostMapping("/users")
	public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userDetails) {
		
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserDto userDto = mapper.map(userDetails, UserDto.class);
		
		UserDto userDto1 = service1.createUser(userDto);
		
		UserResponse userResponse = mapper.map(userDto1, UserResponse.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody UserSignUpRequest userRequest) {
		log.info("START - login()");
		try {
			Authentication authenticate = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword()));
			if (authenticate.isAuthenticated()) {
				log.info("Valid User detected - logged in");
			}
		} catch (BadCredentialsException e) {
			throw new InvalidCredentialsException(BAD_CREDENTIALS_MESSAGE);
		} catch (DisabledException e) {
			throw new InvalidCredentialsException(DISABLED_ACCOUNT_MESSAGE);
		} catch (LockedException e) {
			throw new InvalidCredentialsException(LOCKED_ACCOUNT_MESSAGE);
		}
		String role = service1.getRoleByUserName(userRequest.getUsername());
		String token = jwtUtil.generateToken(userRequest.getUsername());
		
		LoginResponse response = new LoginResponse();
		response.setRole(role);
		response.setToken(token);
		log.info("END - login()");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/firstname/{username}")
	public String getFirstName(@PathVariable String username) {
		String firstname = service1.getFirstNameByUsername(username);
		return firstname;
	}
}
