package com.avp.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserDTO implements Serializable{

	private String email;
	private String password;
	

	public UserDTO() {
		// TODO Auto-generated constructor stub
	}


	public UserDTO(String email, String password) {
		super();
		this.email = email;
		this.password = password;
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


	@Override
	public String toString() {
		return "UserDTO [email=" + email + ", password=" + password + "]";
	}
	
}
