package com.diver.main.user.repository;

import jakarta.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.diver.main.security.model.Role;

@Repository
public class RoleDaoImpl implements RoleDaoInterface {
   @Autowired
    EntityManager em;
   
	@Override
	@Transactional
	public Role getRoleById(Integer id) {
		return em.find(Role.class, id);
	}
}
