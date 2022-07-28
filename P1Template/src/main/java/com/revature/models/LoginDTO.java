package com.revature.models;

public class LoginDTO {
	private String username;
	private String password;
	
	//Constructors
	public LoginDTO() {
		super();
		this.username = "null";
		this.password = "null";
		// TODO Auto-generated constructor stub
	}
	public LoginDTO(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	//setters and getters
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}	
	
}
