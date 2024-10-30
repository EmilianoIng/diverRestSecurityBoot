package com.diver.main.user.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.diver.main.security.model.DettagliAnagrafici;
import com.diver.main.security.model.DettagliAnagraficiDtoInput;

@Repository
public class AnagraficaDaoImpl implements AnagraficaDaoInterface {
	
	@Autowired
	EntityManager em;
	
	@Override
	public void insertDettagli(DettagliAnagrafici dett) {
		
		em.persist(dett);
		
	}

	@Override
	public DettagliAnagrafici retrieveDettagli(String username) {
		// TODO Auto-generated method stub
		return em.find(DettagliAnagrafici.class, username);
	}

	@Override
	public void updateDettagli(DettagliAnagrafici dett) {
		// TODO Auto-generated method stub
		em.merge(dett);
		
	}

}
