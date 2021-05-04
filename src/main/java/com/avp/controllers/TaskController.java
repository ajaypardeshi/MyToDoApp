package com.avp.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
import com.avp.models.TaskDTO;
import com.avp.services.BoardServiceImpl;
import com.avp.services.ColumnServiceImpl;
import com.avp.services.LoginSerciveImpl;
import com.avp.services.TaskServiceImpl;
import com.avp.util.BaseResult;

@RestController
@RequestMapping("/tasks")
public class TaskController {
	
	@Autowired
	private ColumnServiceImpl columnnServiceImpl;
	
	@Autowired
	private TaskServiceImpl taskServiceImpl;
	
	@Autowired
	private LoginSerciveImpl loginServiceImpl;
	
	/*@PostMapping("/{col_id}")
	public ResponseEntity<?> addTasks(@RequestBody Task task,@PathVariable int col_id)
	{
		BaseResult result=new BaseResult();
		System.out.println("col name"+task.getTask_name());
		
		Optional<Colomn> column = columnnServiceImpl.findById(col_id);
		if(!column.isPresent())
		{
			result.setSuccess(false);
			result.setMessage("column not available");
			result.setStatus_code(HttpStatus.BAD_REQUEST.value());
			result.setData(null);
			return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST); 
		}
			
		task.setColumn(column.get());
		Task NewTask =taskServiceImpl.saveTask(task);
		
		TaskDTO tDTO=new TaskDTO();
		tDTO.setTask_id(NewTask.getTask_id());
		tDTO.setTask_name(NewTask.getTask_name());
		tDTO.setTask_description(NewTask.getTask_description());
		
		result.setSuccess(true);
		result.setMessage("Task Created successfully");
		result.setStatus_code(HttpStatus.CREATED.value());
		result.setData(tDTO);
		return new ResponseEntity<>(result , HttpStatus.CREATED);
	}*/


	@PostMapping("/{col_id}")
	public ResponseEntity<?> addTasks(@RequestBody Task task1,@PathVariable int col_id)
	{
		BaseResult result=new BaseResult();
		System.out.println("col name"+task1.getTask_name());
		
		Optional<Colomn> column = columnnServiceImpl.findById(col_id);
		if(!column.isPresent())
		{
			result.setSuccess(false);
			result.setMessage("column not available");
			result.setStatus_code(HttpStatus.BAD_REQUEST.value());
			result.setData(null);
			return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST); 
		}
			
		task1.setColumn(column.get());
		Task NewTask =taskServiceImpl.saveTask(task1);
		
		UserDetails user = NewTask.getColumn().getBoard().getUsrSetails();
		
//		LoginDTO loginDTO=new LoginDTO();
//		loginDTO.setUser_id(user.getUser_ID());
//		loginDTO.setEmail(user.getEmail());
	
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
	
	/*@GetMapping
	public ResponseEntity<?> getAllTasks()
	{
		 BaseResult result=new BaseResult();	
		 
		 List<Task> list = taskServiceImpl.findAll();
		// System.out.println(list);
		 List<TaskDTO>tDTOList=new ArrayList<TaskDTO>();
		 
		 result.setSuccess(true);
		 result.setMessage("");
		 result.setStatus_code(HttpStatus.OK.value());
		
		 if(list!=null)
		 {
			 for(Task task:list)
			 {
				 TaskDTO tDTO=new TaskDTO();
				 tDTO.setTask_id(task.getTask_id());
				 tDTO.setTask_name(task.getTask_name());
				 tDTO.setTask_description(task.getTask_description());
				 tDTOList.add(tDTO);
			 }
			 result.setData(tDTOList);
		 }
		 else
		 {	 
			 result.setData(list);
		 }	 
		return ResponseEntity.ok(result);
		
	}*/

	@GetMapping("/checkboard/{user_id}")
	public ResponseEntity<?>getAllTask(@PathVariable int  user_id)
	{
		
		BaseResult result =  new BaseResult(); 
		UserDetails user ;
		
		Optional<UserDetails> usr = loginServiceImpl.findByID(user_id);
		
		HashMap<String , List<ColumnDTO>>boardMap=new HashMap<String, List<ColumnDTO>>();
		
		ArrayList<TaskDTO>tdtoList=new ArrayList<TaskDTO>();
		ArrayList<ColumnDTO>cdtoList=new ArrayList<ColumnDTO>();
		ArrayList<BoardDTO>bdtoList=new ArrayList<BoardDTO>();
		
		if(usr.isPresent())
		{
			user=usr.get();
			
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
		else
		{
			result.setSuccess(false);
			result.setMessage("user not found !!");
			result.setStatus_code(HttpStatus.BAD_REQUEST.value());
			result.setData(null);
			return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
		}
			
	}
		
	
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?>findByID(@PathVariable("id") int task_id)
	{
		  BaseResult result=new BaseResult();	
		
		 Optional<Task> task = taskServiceImpl.findById(task_id);
		 if(task.isPresent())
		 {
			 TaskDTO tDTO=new TaskDTO();
			 tDTO.setTask_id(task.get().getTask_id());
			 tDTO.setTask_name(task.get().getTask_name());
			 tDTO.setTask_description(task.get().getTask_description());
			
			 result.setSuccess(true);
			 result.setMessage("task found");
			 result.setStatus_code(HttpStatus.OK.value());
			 result.setData(tDTO);
			
			 return ResponseEntity.ok(result);
		 }
		// return new ResponseEntity<>(task,HttpStatus.NOT_FOUND); 
		 //return new ResponseEntity<>("task not found",HttpStatus.NOT_FOUND); 
		 
		 result.setSuccess(false);
		 result.setMessage("task not found");
		 result.setStatus_code(HttpStatus.NOT_FOUND.value());
		 result.setData(null);
		 return ResponseEntity.ok(result);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") int task_id)
	{
		 BaseResult result=new BaseResult();	
		 
		Task task=taskServiceImpl.deleteById(task_id);
		if(task==null)
		{
			//return new ResponseEntity<>(task,HttpStatus.NOT_FOUND);
			 result.setSuccess(false);
			 result.setMessage("task not found");
			 result.setStatus_code(HttpStatus.NOT_FOUND.value());
			 result.setData(null);
			
			 return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
		}
		
		 TaskDTO tDTO=new TaskDTO();
		 tDTO.setTask_id(task.getTask_id());
		 tDTO.setTask_name(task.getTask_name());
		 tDTO.setTask_description(task.getTask_description());
		 
		 result.setSuccess(true);
		 result.setMessage("task deleted");
		 result.setStatus_code(HttpStatus.OK.value());
		 result.setData(tDTO);
		 
		return ResponseEntity.ok(result);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody TaskDTO taskDTO,@PathVariable("id") int task_id)
	{
		 BaseResult result=new BaseResult();	
		Task task1=taskServiceImpl.update(taskDTO ,task_id);
		if(task1==null)
		{
			//return new ResponseEntity<>(task1,HttpStatus.NOT_FOUND);
			result.setSuccess(false);
			result.setMessage("task not found");
			result.setStatus_code(HttpStatus.NOT_FOUND.value());
			result.setData(null);
			
			return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
		}
		
		TaskDTO tDTO=new TaskDTO();
		 tDTO.setTask_id(task1.getTask_id());
		 tDTO.setTask_name(task1.getTask_name());
		 tDTO.setTask_description(task1.getTask_description());
		 
		 result.setSuccess(true);
		 result.setMessage("task updated");
		 result.setStatus_code(HttpStatus.OK.value());
		 result.setData(tDTO);
		 
		 
		return ResponseEntity.ok(result);
	}
	

//	@PatchMapping("/{id}")
//	public ResponseEntity<?> changeStatus(@RequestBody Task task,@PathVariable("id") int id)
//	{
//		if(task.getTask_status().equalsIgnoreCase("todo")||task.getTask_status().equalsIgnoreCase("doing")||task.getTask_status().equalsIgnoreCase("done"))
//		{
//			Task task1=taskServiceImpl.doPatch(task ,id);
//			if(task1==null)
//			{
//					//return new ResponseEntity<>(task,HttpStatus.NOT_FOUND);
//				return new ResponseEntity<>("task not found",HttpStatus.NOT_FOUND);
//			}
//			return ResponseEntity.ok(task1);
//		}
//		else
//		{
//			return new ResponseEntity<>("Invalid status",HttpStatus.BAD_REQUEST);
//		}
//			
//	}
	
}
