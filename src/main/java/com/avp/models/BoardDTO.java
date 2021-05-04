package com.avp.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.avp.entities.Colomn;

public class BoardDTO implements Serializable{

	
	private int board_id;
	
	private String boardName;
	
	private ArrayList<ColumnDTO>columnList;

	public int getBoard_id() {
		return board_id;
	}

	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public ArrayList<ColumnDTO> getColumnList() {
		return columnList;
	}

	public void setColumnList(ArrayList<ColumnDTO> columnList) {
		this.columnList = columnList;
	}

	
}
