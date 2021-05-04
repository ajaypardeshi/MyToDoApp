package com.avp.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.avp.entities.Task;


public interface TaskDao extends JpaRepository<Task, Integer>{

}
