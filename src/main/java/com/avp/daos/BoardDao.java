package com.avp.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avp.entities.Board;

public interface BoardDao extends JpaRepository<Board, Integer>{

}
