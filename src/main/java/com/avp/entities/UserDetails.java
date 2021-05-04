package com.avp.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import com.avp.entities.Board;


@Entity
public class UserDetails {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int user_ID;
	
	@Column(nullable = false , unique = true)
    private String email;
	
	@Column(nullable = false, length = 64)
	private String password;
	     
	private String name;
	
	private Long phone;
	
	@OneToMany(mappedBy = "usrSetails",fetch = FetchType.LAZY,cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE})
	private List<Board>boardList;
	
    public UserDetails() {
	}

	

	public UserDetails(int user_ID, String email, String password, String name, Long phone, List<Board> boardList) {
		super();
		this.user_ID = user_ID;
		this.email = email;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.boardList = boardList;
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


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Long getPhone() {
		return phone;
	}


	public void setPhone(Long phone) {
		this.phone = phone;
	}

	
	public int getUser_ID() {
		return user_ID;
	}


	public void setUser_ID(int user_ID) {
		this.user_ID = user_ID;
	}

	

	public List<Board> getBoardList() {
		return boardList;
	}



	public void setBoardList(List<Board> boardList) {
		this.boardList = boardList;
	}



	@Override
	public String toString() {
		return "UserDetails [user_ID=" + user_ID + ", email=" + email + ", password=" + password + ", name=" + name
				+ ", phone=" + phone + ", boardList=" + boardList + "]";
	}
	
   
}
