package com.diver.main.immagine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diver.main.model.Immagine;
@Repository
public interface ImmagineJpaInterface extends JpaRepository<Immagine, Integer> {

}
