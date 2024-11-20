package com.diver.main.gita.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.diver.main.model.Gita;
import com.diver.main.model.dto.GiteInnerEsperienceDTOInterface;

@Repository
public interface GitaRepoJpaInterface extends JpaRepository<Gita, Integer> {

	@Query(value = "SELECT  gite.nome AS 'nome_gita',   esperienze.id AS 'esperienza_id'   FROM  gite inner join  esperienze on  esperienze.id= gite.id_experience  where gite.nome=?1 ",nativeQuery = true)
	List<GiteInnerEsperienceDTOInterface> giteInnerJoinExperienceWheregitaNome(String nome);
}
