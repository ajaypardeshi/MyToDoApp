package com.avp.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avp.entities.Board;
import com.avp.entities.Colomn;
import com.avp.entities.Task;
import com.avp.entities.UserDetails;
import com.avp.models.BoardDTO;
import com.avp.models.ColumnDTO;
import com.avp.models.LoginDTO;
import com.avp.models.RegisterDTO;
import com.avp.models.TaskDTO;
import com.avp.models.UserDTO;
import com.avp.services.LoginSerciveImpl;
import com.avp.util.BaseResult;
import com.avp.util.ErrorResponse;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private LoginSerciveImpl loginServiceImpl;

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody UserDetails userDetais)
	{
		BaseResult result=new BaseResult();
		try
		{
			if(userDetais.getEmail().equals("")||userDetais.getPassword().equals(""))
			{
				throw new Exception("username and password should not be blank");

			}
			else
			{	
				UserDetails user=loginServiceImpl.registerUser(userDetais);
				RegisterDTO rDTO=new RegisterDTO();
				rDTO.setUser_id(user.getUser_ID());
				rDTO.setEmail(user.getEmail());
				rDTO.setName(user.getName());
				rDTO.setPhone(user.getPhone());

				result.setMessage("Registration success !!");
				result.setStatus_code(HttpStatus.CREATED.value());
				result.setSuccess(true);
				result.setData(rDTO);

				return new ResponseEntity<BaseResult>(result,HttpStatus.CREATED);
			} 
		}
		catch(Exception e)
		{
			result.setSuccess(false);
			result.setStatus_code(HttpStatus.BAD_REQUEST.value());
			result.setData(null);

			if(e instanceof DataIntegrityViolationException)
			{
				result.setMessage("User already exist !!");
			}
			else
			{
				if(userDetais.getEmail().equals("")||userDetais.getEmail().equals(""))
				{
					result.setMessage("User name and password should not be blank !!");
				}
				else
				{
					result.setMessage(e.getMessage());
				}

			}

			return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);

		}
	}


//for dynamic checkboard
	/*@PostMapping("/checkLogin")
	public ResponseEntity<?>login(@RequestBody UserDTO userDTO)
	{
		BaseResult result =  new BaseResult(); 

		UserDetails user = loginServiceImpl.loginCheck(userDTO.getEmail(), userDTO.getPassword());

		HashMap<String , List<ColumnDTO>>boardMap=new HashMap<String, List<ColumnDTO>>();
		
		ArrayList<TaskDTO>tdtoList=new ArrayList<TaskDTO>();
		ArrayList<ColumnDTO>cdtoList=new ArrayList<ColumnDTO>();
		ArrayList<BoardDTO>bdtoList=new ArrayList<BoardDTO>();
		
		if(user!=null)
		{
			LoginDTO loginDTO=new LoginDTO();
			loginDTO.setUser_id(user.getUser_ID());
			loginDTO.setEmail(user.getEmail());
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
			
			for(BoardDTO boardDTO:bdtoList)
			{
				boardMap.put(boardDTO.getBoardName(), boardDTO.getColumnList());
			}

			loginDTO.setBoardMap(boardMap);
			
			result.setSuccess(true);
			result.setMessage("Login success !!");
			result.setStatus_code(HttpStatus.OK.value());
			result.setData(loginDTO);
			
			return new ResponseEntity<>(result,HttpStatus.OK);
			
		}
		else
		{
			result.setSuccess(false);
			result.setMessage("Login failed !!");
			result.setStatus_code(HttpStatus.UNAUTHORIZED.value());
			result.setData(null);
			return new ResponseEntity<>(result,HttpStatus.UNAUTHORIZED);
		}
			
	}*/
	
	@PostMapping("/checkLogin")
	public ResponseEntity<?>login(@RequestBody UserDTO userDTO)
	{
		BaseResult result=new BaseResult();
		
		UserDetails user = loginServiceImpl.loginCheck(userDTO.getEmail(), userDTO.getPassword());
		
		 if(user!=null)
		 {
			 	RegisterDTO rDTO=new RegisterDTO();
			 	rDTO.setName(user.getName());
			 	rDTO.setEmail(user.getEmail());
			 	rDTO.setPhone(user.getPhone());
			 	rDTO.setUser_id(user.getUser_ID());
			 	
			 	result.setSuccess(true);
			 	result.setMessage("Login Success !!");
			 	result.setStatus_code(HttpStatus.OK.value());
			 	result.setData(rDTO);
				return new ResponseEntity<>(result,HttpStatus.OK);
		 }
		 else
		 {
			
			 result.setSuccess(false);
			 result.setMessage("Incorrect username or password");
			 result.setStatus_code(HttpStatus.UNAUTHORIZED.value());
			 result.setData(null);
			
			 return new ResponseEntity<>(result,HttpStatus.UNAUTHORIZED);
		 }	
	}
}
