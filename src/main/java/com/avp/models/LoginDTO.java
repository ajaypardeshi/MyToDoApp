package com.avp.models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.avp.entities.Colomn;

public class LoginDTO implements Serializable{
	
	private int user_id;
	private String email;
	private HashMap<String, List<ColumnDTO>>boardMap;
	
//	private Map<String, List<Colomn>>boardMap;
	
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public HashMap<String, List<ColumnDTO>> getBoardMap() {
		return boardMap;
	}
	public void setBoardMap(HashMap<String, List<ColumnDTO>> boardMap) {
		this.boardMap = boardMap;
	}
	
	
}
