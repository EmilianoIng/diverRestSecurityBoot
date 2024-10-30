package com.diver.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.diver.main.security.model.User;
import com.diver.main.security.model.UserDetailsImpl;
import com.diver.main.user.repository.UserDaoInterface;

@Service
public class SecurityServiceImpl implements SecurityInterface {
@Autowired
UserDaoInterface uDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		System.out.println("email di Emy "+username);
		User userToLog=uDao.findByUsername(username);

		if( userToLog==null)
		{
		      throw new UsernameNotFoundException("Username not found");
		    }
		  
		UserDetailsImpl userAuthenticate=new UserDetailsImpl();
		userAuthenticate.setUserlogIn(userToLog);
	 
		
		userAuthenticate.setUserRuolo();
	return userAuthenticate;    
	}

}
