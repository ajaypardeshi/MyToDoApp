package com.avp.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.avp.daos.ColumnDao;
import com.avp.entities.Colomn;

@Service
@Transactional
public class ColumnServiceImpl {

	@Autowired
	private ColumnDao columnDao;
	
	public Colomn saveColumn(Colomn column)
	{
		return columnDao.save(column);
	}
	
	public List<Colomn> findAll()
	{
		return columnDao.findAll();
	}
	
	public Optional<Colomn> findById(int id)
	{
		return columnDao.findById(id);
	}
	
	public Colomn deleteById(int id)
	{
		Optional<Colomn> Column = columnDao.findById(id);
		if(Column.isPresent())
		{
			columnDao.delete(Column.get());
		}
		return Column.orElse(null);
			
	}

	public Colomn update(Colomn t,int id) {
		Optional<Colomn> Column = columnDao.findById(id);
		if(Column.isPresent())
		{
			Column.get().setColumn_name(t.getColumn_name());
			//Column.get().setColumn_description(t.getColumn_description());
			//Column.get().setColumn_status(t.getColumn_status());
			return columnDao.save(Column.get());
		}
		return null;
	}
	
}
