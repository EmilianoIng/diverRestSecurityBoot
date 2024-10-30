package com.diver.main.esperienza.repository;

import java.util.List;

import com.diver.main.model.Esperienza;
import com.diver.main.model.Gita;
import com.diver.main.model.Immagine;
import com.diver.main.model.Viaggio;
import com.diver.main.model.dto.VideoDtoOut;
import com.diver.main.video.model.Video;

public interface EsperienzaDaoInterface {

	public List<Object[]> viaggiGiteInnerEsperienzeInnerVideo();
	public List<Object[]> viaggioGitaInnerEsperienzeInnerVideoId(int id);
	public List<Object[]> immaginiWhereMainIsTrue();
	public List<Object[]> allMainExperienceImageOfTravel(String name);
	public List<Object[]> allCopertinaImageOfExperience(String name);
	

	public Immagine retrieveNewTravelImage();

	public List<String> allExperienceWithViaggioGitaDetail();

	// public Integer retrieveIdViaggio(String nome);
	// public Integer retrieveIdGita(String nome);
	// public Integer retrieveIdEsperienza(Integer id);
	public Esperienza retrieveEsperienzaFromId(Integer id);

	public List<Object[]> viaggiInnerJoinExperienceWhereviaggioNome(String nome);

	public List<Object[]> giteInnerJoinExperienceWheregitaNome(String nome);

	public int saveVideo(Video v);
	public String removeVideo(int id);
	public Video getVideoFromId(int id);
	public List<Video> getVideoFromExperienceId(int id);
	public void updateVideo(Video v);
	public void updateEsperienza(Esperienza e);
	public void updateImmagine(Immagine i);
	public int saveViaggio(Viaggio v);
	public Viaggio getViaggioFromId(int id);
	public int saveExperience(Esperienza e);
	public Esperienza getEsperienzaFromId(int id);
	public Esperienza getEsperienzaByName(String nome);
	public int saveImmagine(Immagine i);
	public Viaggio getViaggioFromName(String name);
	public List<Object[]> immagineOfTravelWhereMainIsTrue(String name);
	public Immagine getImageFromId(int id);
	public void updateViaggio(Viaggio v);
	public  List<Object[]> retrieveIdEntitytoDeleteByIdTravel(int id);
	public void removeImmagine(int id) ;
	public void removeExperience(int id) ;
	public void removeViaggio(int id) ;
	public List<Object[]> retrieveAllImageOfExperienceById(int id,String tipo);
	public List<Object[]> immaginiGiteWhereExperienceImageIsTrue();
	public int saveGita(Gita g);
	public List<Object[]> getGitaFromIdExperience(int id);
	public void removeGita(int idGita);
	public void updateGita(Gita gEsperienza);
	public List<Object[]> viaggioGitaInnerEsperienzeInnerVideoLastUpdate();
	public List<Object[]> viaggioInnerEsperienzeInnerImmaginiLastUpdate();
	public List<Object[]> gitaInnerEsperienzeInnerImmaginiLastUpdate();


	

}
