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
import com.sun.istack.NotNull;
import com.avp.entities.Colomn;

@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int board_id;
	
	@NotNull
	private String board_name;
	
	@ManyToOne
	@JoinColumn(name = "user_id",referencedColumnName = "user_ID")
	private UserDetails usrSetails;
	
	@OneToMany(mappedBy = "board",fetch = FetchType.LAZY,cascade = {CascadeType.REMOVE})
	private List<Colomn>columnList;
	
	public Board(int board_id, String board_name, UserDetails usrSetails, List<Colomn> columnList) {
		super();
		this.board_id = board_id;
		this.board_name = board_name;
		this.usrSetails = usrSetails;
		this.columnList = columnList;
	}

	public Board() {
		// TODO Auto-generated constructor stub
	}

	public int getBoard_id() {
		return board_id;
	}

	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}

	public String getBoard_name() {
		return board_name;
	}

	public void setBoard_name(String board_name) {
		System.out.println("b setter");
		this.board_name = board_name;
	}

	

	public List<Colomn> getColumnList() {
		return columnList;
	}

	public void setColumnList(List<Colomn> columnList) {
		this.columnList = columnList;
	}

	
	public UserDetails getUsrSetails() {
		return usrSetails;
	}

	public void setUsrSetails(UserDetails usrSetails) {
		this.usrSetails = usrSetails;
	}

	@Override
	public String toString() {
		return "Board [board_id=" + board_id + ", board_name=" + board_name + ", usrSetails=" + usrSetails
				+ ", columnList=" + columnList + "]";
	}

	
}
