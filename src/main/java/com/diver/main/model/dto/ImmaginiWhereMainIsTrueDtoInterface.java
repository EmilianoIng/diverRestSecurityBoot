package com.diver.main.model.dto;

public interface ImmaginiWhereMainIsTrueDtoInterface {
// SELECT t.nome AS nome_viaggio , e.nome  AS nome_esperienza, i.pathImage as pathImage, i.id AS id_immagine, i.mainImage AS main_image, i.isVertical as is_vertical,i.experienceImage as experience_image,t.demo as demo,t.id AS id_viaggio,e.id  AS id_experience FROM Viaggio t JOIN t.esperienze e JOIN e.immagini i where i.mainImage=1"
String getNome_viaggio();
String getNome_esperienza();
String getPathImage();
Integer getId_immagine();
Integer getMain_image();
Integer getIs_Vertical();
Integer getExperience_image();
Integer getDemo();
Integer getId_viaggio();
Integer getId_esperience();
}
