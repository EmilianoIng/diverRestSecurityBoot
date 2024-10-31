package com.diver.main.user.repository;

import jakarta.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.diver.main.security.model.User;
import com.diver.main.security.model.UserRole;

@Repository
public class UserRoleDaoImpl implements UserRoleDaoInterface {

	@Autowired
	EntityManager em;

	@Override
	public void addUserRole(User u) {
		UserRole ur= new UserRole();
		ur.setIdRole(1);
		ur.setIdUser(u.getId());
		em.persist(ur);
       
	}
}
