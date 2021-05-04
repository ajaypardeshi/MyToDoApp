package com.avp.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.avp.entities.UserDetails;

public interface LoginDao extends JpaRepository<UserDetails, Integer> {
	
	@Query("from UserDetails u where u.email=?1") //to return one record from the list
	public UserDetails findByEmail(String email);

}
