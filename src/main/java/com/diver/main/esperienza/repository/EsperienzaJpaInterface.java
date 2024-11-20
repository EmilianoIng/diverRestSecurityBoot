package com.diver.main.esperienza.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.diver.main.model.Esperienza;
import com.diver.main.model.dto.AnyImageOfExperienceInterface;
import com.diver.main.model.dto.GitaFromExperienceInterface;
import com.diver.main.model.dto.GiteImmDtoOutInterface;
import com.diver.main.model.dto.ImmaginiDTOInterface;
import com.diver.main.model.dto.ViaggiDeleteDTOInterface;
import com.diver.main.model.dto.VideoDTOInterface;

@Repository
public interface EsperienzaJpaInterface extends JpaRepository<Esperienza,Integer> {
	@Query(value ="SELECT  viaggi.nome AS 'nome_viaggio',   esperienze.nome AS 'nome_esperienza' ,  video.descrizione ,  video.nome AS 'nome_video', video.thumbnail, video.youtube_id, video.id,video.demo FROM  esperienze inner join viaggi  on  viaggi.id= esperienze.id_viaggio inner join  video on  video.id_esperienza= esperienze.id union SELECT  gite.nome AS 'nome_gita',   esperienze.nome AS 'nome_esperienza' ,  video.descrizione ,  video.nome AS 'nome_video', video.thumbnail, video.youtube_id, video.id ,video.demo FROM  gite inner join  esperienze on  gite.id_experience= esperienze.id inner join  video on  video.id_esperienza= esperienze.id",nativeQuery = true)
	List<VideoDTOInterface> viaggiGiteInnerEsperienzeInnerVideo();
	
	@Query(value ="SELECT  viaggi.nome AS 'nome_viaggio',   esperienze.nome AS 'nome_esperienza' ,  video.descrizione ,  video.nome AS 'nome_video', video.thumbnail, video.youtube_id, video.id,video.demo FROM  viaggi inner join  esperienze on  viaggi.id= esperienze.id_viaggio inner join  video on  video.id_esperienza= esperienze.id where video.id=?1  union SELECT  gite.nome AS 'nome_gita',   esperienze.nome AS 'nome_esperienza' ,  video.descrizione ,  video.nome AS 'nome_video', video.thumbnail, video.youtube_id, video.id ,video.demo FROM  gite inner join  esperienze on  gite.id_experience= esperienze.id inner join  video on  video.id_esperienza= esperienze.id  where video.id=?1",nativeQuery = true)
	List<VideoDTOInterface> viaggioGitaInnerEsperienzeInnerVideoId(int id);
	
	@Query(value ="SELECT viaggi.nome AS nome_viaggio , esperienze.nome  AS nome_esperienza, immagini.path_image, immagini.id AS id_immagine, immagini.main_image, immagini.is_vertical,immagini.experience_image,viaggi.demo,viaggi.id AS id_viaggio,esperienze.id  AS id_experience FROM viaggi inner join  esperienze on  viaggi.id= esperienze.id_viaggio   inner join immagini on  immagini.id_esp= esperienze.id  where immagini.main_image=1",nativeQuery = true)
	List<ImmaginiDTOInterface> immaginiWhereMainIsTrue();
	
	@Query(value ="SELECT viaggi.nome AS nome_viaggio , esperienze.nome  AS nome_esperienza, immagini.path_image, immagini.id AS id_immagine, immagini.main_image, immagini.is_vertical,immagini.experience_image,viaggi.demo,viaggi.id AS id_viaggio,esperienze.id  AS id_experience FROM viaggi inner join  esperienze on  viaggi.id= esperienze.id_viaggio   inner join immagini on  immagini.id_esp= esperienze.id  where viaggi.nome=?1 and immagini.experience_image=1",nativeQuery = true)
	List<ImmaginiDTOInterface> allMainExperienceImageOfTravel(String name);
	
	@Query(value ="SELECT  esperienze.nome  AS nome_esperienza, immagini.path_image, immagini.id AS id_immagine, immagini.is_vertical,immagini.experience_image,esperienze.id  AS id_experience,gite.demo,gite.nome FROM gite  inner join esperienze  on  gite.id_experience=esperienze.id inner join  immagini on   immagini.id_esp= esperienze.id  where immagini.experience_image=1",nativeQuery = true)
	List<GiteImmDtoOutInterface> immaginiGiteWhereExperienceImageIsTrue();
	
	@Query(value ="SELECT e.nome AS nomeEsperienza, \r\n"
			+ "           i.pathImage AS pathImage, \r\n"
			+ "           i.id AS idImmagine, \r\n"
			+ "           i.mainImage AS mainImage, \r\n"
			+ "           i.isVertical AS isVertical,\r\n"
			+ "           i.experienceImage AS experienceImage, \r\n"
			+ "           e.descrizione AS descrizione, \r\n"
			+ "           e.id AS idEsperienza, \r\n"
			+ "           t.demo AS demo, \r\n"
			+ "           e.data AS data \r\n"
			+ "    FROM Viaggio t \r\n"
			+ "    JOIN t.esperienze e \r\n"
			+ "    JOIN e.immagini i \r\n"
			+ "    WHERE e.id = ?1 \r\n"
			+ "    ORDER BY i.experienceImage DESC")
	List<AnyImageOfExperienceInterface> retrieveAllImageOfExperienceById (int id);
	
	
	@Query(value ="SELECT e.nome AS nomeEsperienza, \r\n"
			+ "           i.pathImage AS pathImage, \r\n"
			+ "           i.id AS idImmagine, \r\n"
			+ "           i.mainImage AS mainImage, \r\n"
			+ "           i.isVertical AS isVertical,\r\n"
			+ "           i.experienceImage AS experienceImage, \r\n"
			+ "           e.descrizione AS descrizione, \r\n"
			+ "           e.id AS idEsperienza, \r\n"
			+ "           t.demo AS demo, \r\n"
			+ "           e.data AS data \r\n"
			+ "    FROM Gita t \r\n"
			+ "    JOIN t.esperienze e \r\n"
			+ "    JOIN e.immagini i \r\n"
			+ "    WHERE e.id = ?1 \r\n"
			+ "    ORDER BY i.experienceImage DESC")
	List<AnyImageOfExperienceInterface> retrieveAllImageOfTripExperienceById (int id);
	
	@Query(value ="SELECT distinct esperienze.id AS id_esperienza,  immagini.id AS id_immagine , immagini.path_image AS pathImage  FROM  viaggi inner join  esperienze on  viaggi.id= esperienze.id_viaggio inner join immagini on  immagini.id_esp= esperienze.id  where viaggi.id=?1",nativeQuery = true)
	List<ViaggiDeleteDTOInterface> retrieveIdEntitytoDeleteByIdTravel(int id);
	
	@Query(value ="SELECT viaggi.nome AS 'nome_viaggio'  FROM viaggi UNION SELECT gite.nome FROM gite",nativeQuery = true)
	List<String> allExperienceWithViaggioGitaDetail();
	
	@Query(value ="SELECT  g.id AS id_gita, i.id AS id_imm,i.pathImage AS pathImage FROM Gita g JOIN g.esperienze e JOIN e.immagini i where e.id=?1")
	List<GitaFromExperienceInterface> getGitaFromIdExperience(int id);
	
	List<Esperienza> findByNome(String nome);
	

}
