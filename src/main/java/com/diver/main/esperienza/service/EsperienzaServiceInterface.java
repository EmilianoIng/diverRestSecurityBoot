package com.diver.main.esperienza.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.diver.main.model.Esperienza;
import com.diver.main.model.dto.EspDtoOut;
import com.diver.main.model.dto.EsperienzaDtoInput;
import com.diver.main.model.dto.ExperienceDetailsDtoOut;
import com.diver.main.model.dto.ImmDtoOut;
import com.diver.main.model.dto.NewsOfExperienceDtoOut;
import com.diver.main.model.dto.VideoDtoInput;
import com.diver.main.model.dto.VideoDtoOut;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface EsperienzaServiceInterface {
	
	public List<VideoDtoOut> retrieveAllEsperienzeWithVideoDetail();
	public List<ImmDtoOut> retrieveAllMainImage();
	public List<ExperienceDetailsDtoOut> retrieveAllTripsImage() throws IOException;
	public List<ImmDtoOut> retrieveAllMainExperienceImageOfTravel(String name);
	public List<EspDtoOut> retrieveAllExperience();
	public Integer saveVideo(MultipartFile file,String detail) throws IOException;
	public Integer saveViaggio(MultipartFile file,String detail) throws IOException, ParseException;
	public Integer saveGita(MultipartFile file,String detail) throws IOException, ParseException;
	public Integer saveExperience(int idViaggio,String detail)throws IOException, ParseException ;
	public Integer saveImage(MultipartFile file,int idExperience,String detail) throws IOException;
	public Integer saveExperienceImage(MultipartFile file, String detail) throws IOException;
	String pathFile="C:\\nativescript\\diverbck\\imageBackend\\";
	public byte[] getImage(String name) throws IOException;
	public boolean deleteVideo(int id) throws IOException;
	public boolean deleteImmagine(int id) throws IOException;
	public boolean deleteExperience(int id) throws IOException;
	public VideoDtoOut getVideoById(int id);
	public List<VideoDtoOut> getVideoByExperienceId(int id);
	public void updateVideoWithoutImage(int id,VideoDtoInput vIn);
	public void updateExperienceWithoutImage(int id,EsperienzaDtoInput exIn);
	public void updateVideoAndImage(MultipartFile file, String detail) throws JsonMappingException, JsonProcessingException, IOException;
	public void updateExperienceImage(MultipartFile file, String detail) throws JsonMappingException, JsonProcessingException, IOException;
    public Esperienza retrieveExperienceByName(String name)throws JsonMappingException, JsonProcessingException;
    public List<String> retrieveAllExperienceOfTravel(String name);
    public Esperienza addExperienceOfTravel(String name) throws JsonMappingException, JsonProcessingException;
    public Integer saveDescriptionImageOfTravel(Esperienza e,MultipartFile file,String detail) throws JsonMappingException, JsonProcessingException, IOException;
	public List<ImmDtoOut> modifyMainImageOfTravel(String name, String nameEsp);
	public List<ImmDtoOut> modifyNameOfTravel(String oldname, String newName);
	public boolean deleteViaggio(int idViaggio) throws IOException;
	public List<ExperienceDetailsDtoOut> retrieveAllExperienceImageOfTravel(Integer id,String tipo) throws IOException;
	public void resetMainImageOfTravel(int id);
	public boolean deleteGitaFromIdExperience(int id) throws IOException;
	public NewsOfExperienceDtoOut retrieveNews() throws IOException;
}
