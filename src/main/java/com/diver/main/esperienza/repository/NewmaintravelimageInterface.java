package com.diver.main.esperienza.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diver.main.model.view.Newmaintravelimage;

@Repository
public interface NewmaintravelimageInterface extends JpaRepository<Newmaintravelimage, Integer> {

	Newmaintravelimage findByMainImageAndNomeViaggio(Integer mainImage,String nomeViaggio);
	
	Newmaintravelimage findByNomeEsperienzaAndExperienceImage(String nomeEsperienza,Integer experienceImage);
}
