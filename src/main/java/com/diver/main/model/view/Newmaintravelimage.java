package com.diver.main.model.view;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "newmaintravelimage")
public class Newmaintravelimage {
	@Id
	@Column(name = "id_immagine")
	private Integer idImmagine;
	@Column(name = "nome_viaggio")
	private String nomeViaggio;
	@Column(name = "nome_esperienza")
	private String nomeEsperienza;
	@Column(name = "path_image")
	private String pathImage;
	@Column(name = "main_image")
	private Integer mainImage;
	@Column(name = "is_vertical")
	private Integer isVertical;
	@Column(name = "experience_image")
	private Integer experienceImage;
	@Column(name = "demo")
	private Integer demo;
	@Column(name = "id_viaggio")
	private Integer idViaggio;
	@Column(name = "id_experience")
	private Integer idExperience;
	public Integer getIdImmagine() {
		return idImmagine;
	}
	public void setIdImmagine(Integer idImmagine) {
		this.idImmagine = idImmagine;
	}
	public String getNomeViaggio() {
		return nomeViaggio;
	}
	public void setNomeViaggio(String nomeViaggio) {
		this.nomeViaggio = nomeViaggio;
	}
	public String getNomeEsperienza() {
		return nomeEsperienza;
	}
	public void setNomeEsperienza(String nomeEsperienza) {
		this.nomeEsperienza = nomeEsperienza;
	}
	public String getPathImage() {
		return pathImage;
	}
	public void setPathImage(String pathImage) {
		this.pathImage = pathImage;
	}
	public Integer getMainImage() {
		return mainImage;
	}
	public void setMainImage(Integer mainImage) {
		this.mainImage = mainImage;
	}
	public Integer getIsVertical() {
		return isVertical;
	}
	public void setIsVertical(Integer isVertical) {
		this.isVertical = isVertical;
	}
	public Integer getExperienceImage() {
		return experienceImage;
	}
	public void setExperienceImage(Integer experienceImage) {
		this.experienceImage = experienceImage;
	}
	public Integer getDemo() {
		return demo;
	}
	public void setDemo(Integer demo) {
		this.demo = demo;
	}
	public Integer getIdViaggio() {
		return idViaggio;
	}
	public void setIdViaggio(Integer idViaggio) {
		this.idViaggio = idViaggio;
	}
	public Integer getIdExperience() {
		return idExperience;
	}
	public void setIdExperience(Integer idExperience) {
		this.idExperience = idExperience;
	}
	
	

}
