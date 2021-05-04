package com.avp.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.avp.entities.Task;

public class ColumnDTO implements Serializable{

	
	private int col_id;
	private String col_name;
	private ArrayList<TaskDTO>taskList;

	public int getCol_id() {
		return col_id;
	}
	public void setCol_id(int col_id) {
		this.col_id = col_id;
	}
	public String getCol_name() {
		return col_name;
	}
	public void setCol_name(String col_name) {
		this.col_name = col_name;
	}
	public ArrayList<TaskDTO> getTaskList() {
		return taskList;
	}
	public void setTaskList(ArrayList<TaskDTO> taskList) {
		this.taskList = taskList;
	}
	
	
}
