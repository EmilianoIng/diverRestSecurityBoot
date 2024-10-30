package com.diver.main.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "immagini")
public class Immagine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Boolean isVertical;
	private String pathImage;
	private Boolean mainImage;
	private Boolean experienceImage;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_esp")
	Esperienza espImm;
	@CreationTimestamp
	private LocalDateTime startDate;
	@UpdateTimestamp
	private LocalDateTime lastUpdate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Boolean isVertical() {
		return isVertical;
	}
	public void setVertical(Boolean isVertical) {
		this.isVertical = isVertical;
	}
	public String getPathImage() {
		return pathImage;
	}
	public void setPathImage(String pathImage) {
		this.pathImage = pathImage;
	}
	public Boolean isMainImage() {
		return mainImage;
	}
	public void setMainImage(Boolean mainImage) {
		this.mainImage = mainImage;
	}
	public Boolean isExperienceImage() {
		return experienceImage;
	}
	public void setExperienceImage(Boolean experienceImage) {
		this.experienceImage = experienceImage;
	}
	public Esperienza getEspImm() {
		return espImm;
	}
	public void setEspImm(Esperienza espImm) {
		this.espImm = espImm;
	}

}
