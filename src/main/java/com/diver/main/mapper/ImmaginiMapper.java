package com.diver.main.mapper;

import java.util.Date;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.diver.main.model.dto.ImmaginiDTOInterface;
import com.diver.main.model.dto.AnyImageOfExperienceInterface;
import com.diver.main.model.dto.ExperienceDetailsDtoOut;
import com.diver.main.model.dto.GiteImmDtoOutInterface;
import com.diver.main.model.dto.ImmDtoOut;

@Component
@Mapper(componentModel = "spring")
public interface ImmaginiMapper {
	@Mapping(source = "nome_viaggio",target = "nameViaggioGita")
	@Mapping(source = "nome_esperienza",target = "nameEsperienza")
	@Mapping(source = "pathImage",target = "pathImage")
	@Mapping(source = "id_immagine",target = "id")
	@Mapping(target = "isMainImage", expression = "java(i.getMainImage()!=null  && i.getMainImage() == 1 ?true:false)")
	@Mapping(target = "isVertical", expression = "java(i.getIsVertical()!=null  && i.getIsVertical() == 1?true:false)")
	@Mapping(target = "isExperienceImage", expression = "java(i.getExperienceImage()!=null  && i.getExperienceImage() == 1?true:false)")
	@Mapping(target = "isDemo", expression = "java(i.getDemo()!=null && i.getDemo() == 1?true:false)")
	@Mapping(source = "id_viaggio",target = "idViaggio")
	@Mapping(source = "id_experience",target = "idEsperienza")
	@IterableMapping(elementTargetType = ImmaginiDTOInterface.class)
	public ImmDtoOut toVideoDtoOut(ImmaginiDTOInterface i);
	
	
	@Mapping(target = "nameEsperienza",expression = "java(i.getNome_esperienza().concat(\" - \").concat(i.getNome()))")
	@Mapping(source = "path_image",target = "pathImage")
	@Mapping(source = "id_immagine",target = "id")
	@Mapping(source = "id_experience",target = "idEsperienza")
	@Mapping(target = "vertical", expression = "java(i.getIs_vertical()!=null  && i.getIs_vertical() == 1?true:false)")
	@Mapping(target = "experienceImage", expression = "java(i.getExperience_image()!=null  && i.getExperience_image() == 1?true:false)")
	@Mapping(target = "demo", expression = "java(i.getDemo()!=null && i.getDemo() == 1?true:false)")
	@IterableMapping(elementTargetType = GiteImmDtoOutInterface.class)
	public ExperienceDetailsDtoOut toExperienceImmDtoOut(GiteImmDtoOutInterface i);
	
	
	@Mapping(source ="nomeEsperienza",target = "nameEsperienza")
	@Mapping(source = "pathImage",target = "pathImage")
	@Mapping(target = "mainImage", expression = "java(i.getMainImage()!=null  && i.getMainImage() == 1?true:false)")
	@Mapping(source = "idImmagine",target = "id")
	@Mapping(source = "idEsperienza",target = "idEsperienza")
	@Mapping(source = "descrizione",target = "descrizione")
	@Mapping(target = "vertical", expression = "java(i.getIsVertical()!=null  && i.getIsVertical() == 1?true:false)")
	@Mapping(target = "experienceImage", expression = "java(i.getExperienceImage() == 1?true:false)")
	@Mapping(target = "demo", expression = "java(i.getDemo()!=null && i.getDemo() == 1?true:false)")
	@Mapping(source = "data",target = "data")
	@IterableMapping(elementTargetType = AnyImageOfExperienceInterface.class)
	public ExperienceDetailsDtoOut toAnyExperienceImmDtoOut(AnyImageOfExperienceInterface i);
	
//	@Mapping(source ="nome_viaggio",target = "nameViaggioGita")
//	@Mapping(source = "pathImage",target = "pathImage")
//	@Mapping(source = "nome_esperienza",target = "nameEsperienza")
//	@Mapping(source = "id_immagine",target = "id")
//	@Mapping(target = "isMainImage", expression = "java(i.getMainImage()!=null  && i.getMainImage() == 1?true:false)")
//	@Mapping(target = "vertical", expression = "java(i.getIsVertical()!=null  && i.getIsVertical() == 1?true:false)")
//	@Mapping(target = "experienceImage", expression = "java(i.getExperienceImage() == 1?true:false)")
//	@Mapping(target = "demo", expression = "java(i.getDemo()!=null && i.getDemo() == 1?true:false)")
//	@Mapping(source = "id_viaggio",target = "idViaggio")
//	@Mapping(source = "id_experience",target = "idEsperienza")
//	@IterableMapping(elementTargetType = ImmaginiDTOInterface.class)
//	public ImmDtoOut  immaginiDTOInterfaceToImmDto(ImmaginiDTOInterface i);	
	/*
	 * 			String getNome_viaggio();
	String getNome_esperienza();
	String getPathImage();
	Integer getId_immagine();
	Integer getMainImage();
	Integer getIsVertical();
	Integer getExperienceImage();
	Integer getDemo();
	Integer getId_viaggio();
	Integer getId_experience();
	 * */
	
}
