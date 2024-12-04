package com.diver.main.model.dto;
//"SELECT  esperienze.nome  AS nome_esperienza, immagini.path_image, immagini.id AS id_immagine, immagini.is_vertical,immagini.experience_image,esperienze.id  AS id_experience,gita.demo,gita.nome FROM gita  inner join   on  gite.id_experience=esperienze.id inner join  immagini on on  immagini.id_esp= esperienze.id  where immagini.experience_image=1"
public interface GiteImmDtoOutInterface {
	
	String getNome_esperienza();
	String getPath_image();
	Integer getId_immagine();
	Integer getIs_vertical();
	Integer getExperience_image();
	Integer getId_experience();
	Integer getDemo();
	String getNome();
}
