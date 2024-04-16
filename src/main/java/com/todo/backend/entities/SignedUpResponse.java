package com.todo.backend.entities;

public class SignedUpResponse {
	
	private String signupMessage;

	public SignedUpResponse(String signupMessage) {
		super();
		this.signupMessage = signupMessage;
	}

	public String getSignupMessage() {
		return signupMessage;
	}

	public void setSignupMessage(String signupMessage) {
		this.signupMessage = signupMessage;
	}

	@Override
	public String toString() {
		return "SignedUpResponse [signupMessage=" + signupMessage + "]";
	};
	
}
