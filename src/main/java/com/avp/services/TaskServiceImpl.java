package com.avp.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.avp.daos.TaskDao;
import com.avp.entities.Task;
import com.avp.models.TaskDTO;

@Service
@Transactional
public class TaskServiceImpl {

	
	@Autowired
	private TaskDao taskDao;
	
	
	public Task saveTask(Task task)
	{
		return taskDao.save(task);
	}
	
	public List<Task> findAll()
	{
		return taskDao.findAll();
	}
	
	public Optional<Task> findById(int id)
	{
		return taskDao.findById(id);
	}
	
	public Task deleteById(int id)
	{
		Optional<Task> task = taskDao.findById(id);
		if(task.isPresent())
		{
			taskDao.delete(task.get());
		}
		return task.orElse(null);
			
	}

	public Task update(TaskDTO t,int id) {
		Optional<Task> task = taskDao.findById(id);
		if(task.isPresent())
		{
			task.get().setTask_name(t.getTask_name());
			task.get().setTask_description(t.getTask_description());
			//task.get().setTask_status(t.getTask_status());
			return taskDao.save(task.get());
		}
		return null;
	}

	public Task doPatch(Task t, int id) {
		Optional<Task> task=taskDao.findById(id);
		if(task.isPresent())
		{
			//task.get().setTask_status(t.getTask_status());
			return taskDao.save(task.get());
		}
		return null;
	}
}
