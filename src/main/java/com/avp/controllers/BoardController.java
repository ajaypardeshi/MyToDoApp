package com.avp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avp.daos.BoardDao;
import com.avp.daos.LoginDao;
import com.avp.entities.Board;
import com.avp.entities.Task;
import com.avp.entities.UserDetails;
import com.avp.models.BoardDTO;
import com.avp.services.BoardServiceImpl;
import com.avp.util.BaseResult;

@RestController
@RequestMapping("/boards")
public class BoardController {

	@Autowired
	private BoardServiceImpl boardServiceImpl;
	@Autowired
	private LoginDao loginDao;
	
	@PostMapping("/{user_id}")
	public ResponseEntity<?> addBoards(@RequestBody Board board,@PathVariable int user_id)
	{
		BaseResult result=new BaseResult();
		
		
		Optional<UserDetails> user = loginDao.findById(user_id);
		if(!user.isPresent())
		{
			result.setSuccess(false);
			result.setMessage("user not available");
			result.setStatus_code(HttpStatus.BAD_REQUEST.value());
			result.setData(null);
			
			return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST); 
		}
		
		board.setUsrSetails(user.get());
		
		Board newBoard =boardServiceImpl.saveBoard(board,user_id);
		BoardDTO bDTO=new BoardDTO();
		
		bDTO.setBoard_id(newBoard.getBoard_id());
		bDTO.setBoardName(newBoard.getBoard_name());
		bDTO.setColumnList(null);
		
		result.setSuccess(true);
		result.setMessage("Board Created successfully");
		result.setStatus_code(HttpStatus.CREATED.value());
		result.setData(bDTO);
		
		
		return new ResponseEntity<>(result , HttpStatus.CREATED);
	}
	
	/*@GetMapping
	public ResponseEntity<?> getAllBoards()
	{
		List<Board> list = boardServiceImpl.findAll();
		System.out.println(list);
		return ResponseEntity.ok(list);
	}*/
	
	/*
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Board board,@PathVariable("id") int id)
	{
		Board board1=boardServiceImpl.update(board ,id);
		if(board1==null)
		{
			//return new ResponseEntity<>(task1,HttpStatus.NOT_FOUND);
			return new ResponseEntity<>("board not found",HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(board1);
	}
	*/
	
}
