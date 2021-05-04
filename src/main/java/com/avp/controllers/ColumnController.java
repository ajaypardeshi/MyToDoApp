package com.avp.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.avp.daos.LoginDao;
import com.avp.entities.Board;
import com.avp.entities.Colomn;
import com.avp.entities.UserDetails;
import com.avp.models.BoardDTO;
import com.avp.models.ColumnDTO;
import com.avp.services.BoardServiceImpl;
import com.avp.services.ColumnServiceImpl;
import com.avp.util.BaseResult;

@Controller
@RequestMapping("/columns")
public class ColumnController {
	
	@Autowired
	private ColumnServiceImpl columnnServiceImpl;
	
	@Autowired
	private BoardServiceImpl boardServiceImpl;
	
	@PostMapping("/{board_id}")
	public ResponseEntity<?> addBoards(@RequestBody Colomn col,@PathVariable int board_id)
	{
		BaseResult result=new BaseResult();
		System.out.println("col name"+col.getColumn_name());
		
		Optional<Board> board = boardServiceImpl.findById(board_id);
		if(!board.isPresent())
		{
			result.setSuccess(false);
			result.setMessage("board not available");
			result.setStatus_code(HttpStatus.BAD_REQUEST.value());
			result.setData(null);
			return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST); 
		}
			
		col.setBoard(board.get());
		
		Colomn Newcolm =columnnServiceImpl.saveColumn(col);
		
		ColumnDTO cDTO=new ColumnDTO();
		
		cDTO.setCol_id(Newcolm.getColumn_id());
		cDTO.setCol_name(Newcolm.getColumn_name());
		cDTO.setTaskList(null);
		
			
		result.setSuccess(true);
		result.setMessage("Column Created successfully");
		result.setStatus_code(HttpStatus.CREATED.value());
		result.setData(cDTO);
		return new ResponseEntity<>(result , HttpStatus.CREATED);
	}

}
