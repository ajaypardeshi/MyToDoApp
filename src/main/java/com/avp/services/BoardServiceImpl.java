package com.avp.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.avp.daos.BoardDao;
import com.avp.daos.LoginDao;
import com.avp.daos.TaskDao;
import com.avp.entities.Board;
import com.avp.entities.Task;
import com.avp.entities.UserDetails;

@Service
@Transactional
public class BoardServiceImpl {

	
	@Autowired
	private BoardDao boardDao;
	
	@Autowired
	private LoginDao loginDao; 
	
	public Board saveBoard(Board board,int user_id)
	{
		
		return boardDao.save(board);
	}
	
	public List<Board> findAll()
	{
		return boardDao.findAll();
	}
	
	public Optional<Board> findById(int id)
	{
		return boardDao.findById(id);
	}
	
	public Board deleteById(int id)
	{
		Optional<Board> board = boardDao.findById(id);
		if(board.isPresent())
		{
			boardDao.delete(board.get());
		}
		return board.orElse(null);
			
	}

	public Board update(Board t,int id) {
		Optional<Board> board = boardDao.findById(id);
		if(board.isPresent())
		{
			board.get().setBoard_name(t.getBoard_name());
			//task.get().setTask_status(t.getTask_status());
			return boardDao.save(board.get());
		}
		return null;
	}

}
