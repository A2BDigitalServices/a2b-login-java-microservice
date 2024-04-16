package com.todo.backend.entities;

public class LoggedInResponse {
	private String logginMessage;

	public LoggedInResponse(String logginMessage) {
		super();
		this.logginMessage = logginMessage;
	}

	public String getLogginMessage() {
		return logginMessage;
	}

	public void setLogginMessage(String logginMessage) {
		this.logginMessage = logginMessage;
	}

	@Override
	public String toString() {
		return "LoggedInResponse [logginMessage=" + logginMessage + "]";
	};
	
}
