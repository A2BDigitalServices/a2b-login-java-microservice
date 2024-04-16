package com.todo.backend.entities;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRequest {
	@NotNull(message="firstname can not be null")
	@Size(min=2,message="firstname must not be less than 2 characters")
	private String firstname;
	
	@NotNull(message="lastname can not be null")
	@Size(min=2,message="lastname must not be less than 2 characters")
	private String lastname;
	
	@NotNull(message="email can not be null")
	@Email
	private String email;
	
	@NotNull(message="password can not be null")
	@Size(min=2,max=16,message="password must be greater than 8 and lessthan 16 chars")
	private String password;
	
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
