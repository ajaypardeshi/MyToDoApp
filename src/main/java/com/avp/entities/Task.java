package com.avp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sun.istack.NotNull;

@Entity
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int task_id;
	
	@Column
	@NotNull
	private String task_name;
	
	@Column
	private String task_description;
	
	
	@ManyToOne()
	@JoinColumn(name = "column_id",referencedColumnName = "column_id")
	private Colomn column;
	
	public Task() {
		// TODO Auto-generated constructor stub
	}


	public Task(int task_id, String task_name, String task_description, Colomn column) {
		super();
		this.task_id = task_id;
		this.task_name = task_name;
		this.task_description = task_description;
		this.column = column;
	}


	public int getTask_id() {
		return task_id;
	}

	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}

	public String getTask_name() {
		return task_name;
	}

	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}

	public String getTask_description() {
		return task_description;
	}

	public void setTask_description(String task_description) {
		this.task_description = task_description;
	}


	public Colomn getColumn() {
		return column;
	}


	public void setColumn(Colomn column) {
		this.column = column;
	}


	@Override
	public String toString() {
		return "Task [task_id=" + task_id + ", task_name=" + task_name + ", task_description=" + task_description
				+ ", column=" + column + "]";
	}


}
