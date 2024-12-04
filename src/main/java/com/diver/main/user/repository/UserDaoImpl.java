package com.diver.main.user.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.diver.main.exception.UserNotFoundException;
import com.diver.main.security.model.User;

@Repository
public class UserDaoImpl implements UserDaoInterface {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public void addUser(User u) {
		// TODO Auto-generated method stub
	

			em.persist(u);
		
		
	}

	@Override
	@Transactional
	public User findByUsername(String email) {
		// TODO Auto-generated method stub
		User result=null;
		Query q = em.createNamedQuery("User.findByUsername");
		q.setParameter(1, email);
		try {
			result = (User) q.getSingleResult();
		} catch (NoResultException ex) {
			
			//new UserNotFoundException(ex.getMessage());
		return	result;
		}
		System.out.println("query effettuata: "+result);
		return result;

	}

	@Override
	@Transactional
	public User update(User u) {
		// TODO Auto-generated method stub
		User uToUpdate=em.find(User.class, u.getId());
		uToUpdate.setEnabled(u.isEnabled());
		em.persist(uToUpdate);
		return uToUpdate;
	}

	@Override
	public void deleteUser(User u) {
		// TODO Auto-generated method stub
		em.persist(u);
		em.remove(u);
		
	}

}
