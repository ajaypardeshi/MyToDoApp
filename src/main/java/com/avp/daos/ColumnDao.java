package com.avp.daos;


import org.springframework.data.jpa.repository.JpaRepository;

import com.avp.entities.Colomn;

public interface ColumnDao extends JpaRepository<Colomn, Integer>{

}
