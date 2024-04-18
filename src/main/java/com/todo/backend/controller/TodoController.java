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
public class TodoController {
	
	
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
	
//	@GetMapping("/auth")
//	public AuthenticationBean authenticate() {
//		return new AuthenticationBean("You are authorized user");
//	}
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
	public ResponseEntity<String> login(@RequestBody UserSignUpRequest userRequest) {
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

		String token = jwtUtil.generateToken(userRequest.getUsername());
		log.info("END - login()");
		return new ResponseEntity<>(token, HttpStatus.OK);
	}
		
	@PostMapping("/createtodo")
	public Todo createTodo(@RequestBody Todo todo) {
		service.createTodo(todo);
		return todo;
	}
	
	@GetMapping("/todos")
	public List<Todo> getTodos(){
		return service.getTodos();
	}
	
	@GetMapping("/todos/{id}")
	public Todo getTodo(@PathVariable long id) {
		return service.getTodo(id);
	}
	
	@DeleteMapping("/todos/delete/{id}")
	public DeletedMsg deleteTodo(@PathVariable long id) {
		service.deleteTodo(id);
		return new DeletedMsg("Todo has been deleted");
				
	}
	
	@PutMapping("/todos/update/{id}")
	public Todo updateTodo(@PathVariable long id,@RequestBody Todo todo) {
		return service.updateTodo(todo, id);
	}
	
}
