package com.diver.main.viaggi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.diver.main.model.Viaggio;
import com.diver.main.model.dto.ExperienceOfTravelDTOInterface;
import com.diver.main.model.dto.ImmaginiDTOInterface;

public interface ViaggiJpaInterface extends JpaRepository<Viaggio, Integer> {
	
	List<Viaggio> findByNome(String nome);
	
	@Query(value ="SELECT distinct  viaggi.nome AS 'nome_viaggio',   esperienze.id AS 'esperienza_id', esperienze.nome AS 'nome_esperienza'   FROM  viaggi inner join  esperienze on  viaggi.id= esperienze.id_viaggio inner join immagini on esperienze.id=immagini.id_esp  where viaggi.nome=?1 order by immagini.main_image desc" ,nativeQuery=true)
	List <ExperienceOfTravelDTOInterface> viaggiInnerJoinExperienceWhereviaggioNome(String nome);
// SELECT t.nome AS nome_viaggio , e.nome  AS nome_esperienza, i.pathImage, i.id AS id_immagine, i.mainImage, i.isVertical,i.experienceImage,t.demo,t.id AS id_viaggio,e.id  AS id_experience FROM Viaggio t JOIN t.esperienze e JOIN e.immagini i where i.mainImage=1
//	@Query(value ="SELECT t.nome AS nome_viaggio , e.nome  AS nome_esperienza, i.pathImage as pathImage, i.id AS id_immagine, i.mainImage AS main_image, i.isVertical as is_vertical,i.experienceImage as experience_image,t.demo as demo,t.id AS id_viaggio,e.id  AS id_experience FROM Viaggio t JOIN t.esperienze e JOIN e.immagini i where i.mainImage=1" )
//	List <ImmaginiDTOInterface> immaginiWhereMainIsTrue();
}
