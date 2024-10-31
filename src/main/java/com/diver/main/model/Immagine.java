package com.diver.main.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "immagini")
public class Immagine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer isVertical;
	private String pathImage;
	private Integer mainImage;
	private Integer experienceImage;
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
	public Integer isVertical() {
		return isVertical;
	}
	public void setVertical(Integer isVertical) {
		this.isVertical = isVertical;
	}
	public String getPathImage() {
		return pathImage;
	}
	public void setPathImage(String pathImage) {
		this.pathImage = pathImage;
	}
	public Integer isMainImage() {
		return mainImage;
	}
	public void setMainImage(Integer mainImage) {
		this.mainImage = mainImage;
	}
	public Integer isExperienceImage() {
		return experienceImage;
	}
	public void setExperienceImage(Integer experienceImage) {
		this.experienceImage = experienceImage;
	}
	public Esperienza getEspImm() {
		return espImm;
	}
	public void setEspImm(Esperienza espImm) {
		this.espImm = espImm;
	}

}
