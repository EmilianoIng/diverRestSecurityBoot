package com.diver.main.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.diver.main.model.dto.VideoDTOInterface;
import com.diver.main.model.dto.VideoDtoOut;
import com.diver.main.video.model.Video;




@Component
@Mapper(componentModel = "spring")
public interface VideoMapper {
	
	@Mapping(source = "nome_viaggio",target = "nomeGitaViaggio")
	@Mapping(source = "nome_esperienza",target = "nome")
	@Mapping(source = "youtube_id",target = "youTubeId")
	@Mapping(source = "descrizione",target = "descrizioneVideo")
	@Mapping(source = "id",target = "id")
	@Mapping(source = "thumbnail",target = "thumbnail")
	@Mapping(target = "demo", expression = "java(v.getDemo() == 1?true:false)")
	@IterableMapping(elementTargetType = VideoDTOInterface.class)
	public VideoDtoOut toVideoDtoOut(VideoDTOInterface v);
	
	
	@Mapping(source = "demo",target = "demo")
	@Mapping(source = "nome",target = "nome")
	@Mapping(source = "youtube",target = "youTubeId")
	@Mapping(source = "descrizione",target = "descrizioneVideo")
	@Mapping(source = "thumbnail",target = "thumbnail")
	@Mapping(source = "id",target = "id")
	@IterableMapping(elementTargetType = Video.class)
	public VideoDtoOut fromVideoToVideoDtoOut(Video v);
}
