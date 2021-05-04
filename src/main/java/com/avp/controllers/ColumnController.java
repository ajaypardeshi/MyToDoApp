package com.avp.controllers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
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
import com.avp.entities.Task;
import com.avp.entities.UserDetails;
import com.avp.models.BoardDTO;
import com.avp.models.ColumnDTO;
import com.avp.models.TaskDTO;
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
	
	/*@PostMapping("/{board_id}")
	public ResponseEntity<?> addColumn(@RequestBody Colomn col,@PathVariable int board_id)
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
	}*/
	
	@PostMapping("/{board_id}")
	public ResponseEntity<?> addColumn(@RequestBody Colomn colm,@PathVariable int board_id)
	{
		BaseResult result=new BaseResult();
		System.out.println("col name"+colm.getColumn_name());
		
		Optional<Board> board1 = boardServiceImpl.findById(board_id);
		if(!board1.isPresent())
		{
			result.setSuccess(false);
			result.setMessage("board not available");
			result.setStatus_code(HttpStatus.BAD_REQUEST.value());
			result.setData(null);
			return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST); 
		}
			
		colm.setBoard(board1.get());
		Colomn Newcolm =columnnServiceImpl.saveColumn(colm);
		
		UserDetails user = Newcolm.getBoard().getUsrSetails();
		
		ArrayList<TaskDTO>tdtoList=new ArrayList<TaskDTO>();
		ArrayList<ColumnDTO>cdtoList=new ArrayList<ColumnDTO>();
		ArrayList<BoardDTO>bdtoList=new ArrayList<BoardDTO>();
		
		List<Board> boardList = user.getBoardList();

		if(boardList!=null)
		{
			for(Board board:boardList)
			{
				if(board!=null)
				{

					BoardDTO bdto=new BoardDTO();
					bdto.setBoard_id(board.getBoard_id());
					bdto.setBoardName(board.getBoard_name());

					List<Colomn> cList = board.getColumnList();

					if(cList!=null)
					{
						for(Colomn col:cList)
						{
							if(col!=null)
							{
								ColumnDTO cdto=new ColumnDTO();
								cdto.setCol_id(col.getColumn_id());
								cdto.setCol_name(col.getColumn_name());

								List<Task> taskList = col.getTaskList();

								if(taskList!=null)
								{
									for(Task task:taskList)
									{
										TaskDTO tdto=new TaskDTO();
										tdto.setTask_id(task.getTask_id());
										tdto.setTask_name(task.getTask_name());
										tdto.setTask_description(task.getTask_description());
										tdtoList.add(tdto);
									}
									cdto.setTaskList(tdtoList);
									System.out.println(tdtoList);
								}
								else
								{
									cdto.setTaskList(null);
								}
								
								cdtoList.add(cdto);
							}
						}
					}

					bdto.setColumnList(cdtoList);
					System.out.println(cdtoList);
					
					bdtoList.add(bdto);
				}
			}
		}
		
		LinkedHashMap<String , Object>boardMap2=new LinkedHashMap<String, Object>();	
		for(BoardDTO boardDTO:bdtoList)
		{
			boardMap2.put("name", boardDTO.getBoardName());
			boardMap2.put("columns", boardDTO.getColumnList());
			
			//boardMap.put(boardDTO.getBoardName(), boardDTO.getColumnList());
		}
		
		result.setSuccess(true);
		result.setMessage("");
		result.setStatus_code(HttpStatus.OK.value());
		result.setData(boardMap2);
		
		return new ResponseEntity<>(result,HttpStatus.OK);
	}

}
