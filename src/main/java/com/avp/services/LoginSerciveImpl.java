package com.avp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.avp.daos.LoginDao;
import com.avp.entities.UserDetails;

@Service
@Transactional
public class LoginSerciveImpl {

	@Autowired
	private LoginDao loginDao;
	
	/*
	 * @Autowired private EntityManager entityManager; //you can use its method too
	 */	
	
	@Transactional
	public UserDetails loginCheck(String email , String password) {
		
		UserDetails user = loginDao.findByEmail(email);
		if(user!=null && user.getEmail()!=null && user.getPassword()!=null)
		{
			if(user.getEmail().trim().equalsIgnoreCase(email.trim()) && user.getPassword().trim().equalsIgnoreCase(password.trim()))
			{
			   return user;
			}
		}
		return null;
		
	}
	
	@Transactional
	public UserDetails registerUser(UserDetails  user) {
		return loginDao.save(user);
				
	}

	public Optional<UserDetails> findByID(int user_id) {
		// TODO Auto-generated method stub
		return loginDao.findById(user_id);
	}


}