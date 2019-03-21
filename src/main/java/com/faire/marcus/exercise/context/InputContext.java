package com.faire.marcus.exercise.context;

import org.springframework.stereotype.Component;

@Component
public class InputContext {
	
	private String token;
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getToken() {
		return token;
	}

}
