package com.diver.main.video.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.diver.main.video.model.Video;

@Repository
public class VideoDaoImpl implements VideoDaoInterface {
	
	@Autowired
    EntityManager em;
	
	@Override
	public List<Video> retrieveAllVideo() {
		// TODO Auto-generated method stub
		TypedQuery<Video> q =em.createQuery("SELECT v FROM Video v", Video.class);
		List<Video> v=q.getResultList();
		return v;
	}
	
	

}
