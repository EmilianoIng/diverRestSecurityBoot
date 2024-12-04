package com.diver.main.user.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.diver.main.security.model.VerificationToken;

@Repository
public class TokenDaoImpl implements TokenDaoInterface {
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	@Override
	public VerificationToken addToken(VerificationToken t) {
		// TODO Auto-generated method stub
		em.persist(t);
		em.flush(); // lo persiste anzitempo dopodiche passa a detached
		em.merge(t); // il merge fa passare una entity dallo stato detached allo stato attached o managed
		return em.find(VerificationToken.class,t.getId());
	}

	@Override
	public VerificationToken getVerificationTokenFromToken(String u) {
		// TODO Auto-generated method stub
		VerificationToken search= new VerificationToken();
	Query q= em.createNamedQuery("VerificationToken.findByToken");
	q.setParameter(1, u);
	try {
	search=(VerificationToken)q.getSingleResult();}
	catch(NoResultException ex) {
		System.out.println("errore: "+ex.getMessage());
		search = null;
	}
		return search;
	}

	@Transactional
	@Override
	public void deleteToken(VerificationToken token) {
		// TODO Auto-generated method stub
		em.persist(token);
		em.remove(token);
		
	}

}
