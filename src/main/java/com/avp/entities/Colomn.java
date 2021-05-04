package com.avp.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import com.avp.entities.Colomn;
import com.sun.istack.NotNull;


@Entity
public class Colomn {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int column_id;
	
	@NotNull
	private String column_name;
	
	
	@OneToMany(mappedBy = "column",fetch = FetchType.LAZY,cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE})
	private List<Task>taskList;
	
	@ManyToOne()
	@JoinColumn(name = "board_id",referencedColumnName = "board_id")
	private Board board;
	
	public Colomn() {
		System.out.println("con call");
		
	}

	public Colomn(int column_id, String column_name, List<Task> taskList, Board board) {
		super();
		this.column_id = column_id;
		this.column_name = column_name;
		this.taskList = taskList;
		this.board = board;
	}

	public int getColumn_id() {
		return column_id;
	}

	public void setColumn_id(int column_id) {
		this.column_id = column_id;
	}

	public String getColumn_name() {
		return column_name;
	}

	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}

	public List<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	@Override
	public String toString() {
		return "Colomn [column_id=" + column_id + ", column_name=" + column_name + ", taskList=" + taskList + ", board="
				+ board + "]";
	}
	
	
	
}
