package com.avp.models;

public class RegisterDTO {

	private int user_id;
	private String name;
	private String email;
	private Long phone;
	
	public RegisterDTO() {
		// TODO Auto-generated constructor stub
	}

	public RegisterDTO(int user_id, String name, String email, Long phone) {
		super();
		this.user_id = user_id;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}
	
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "RegisterDTO [user_id=" + user_id + ", name=" + name + ", email=" + email + ", phone=" + phone + "]";
	}
	
	
}
