package com.diver.main.model.dto;

import java.util.Date;

public class ExperienceDetailsDtoOut {
	
	private Integer id;
	private String pathImage;
	private String nameEsperienza;
	private boolean isVertical;
	private boolean isMainImage;
	private boolean isExperienceImage;
	private boolean isDemo;
	private String descrizione;
	private Integer idEsperienza;
	private byte[] picByte;
	private Date data;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPathImage() {
		return pathImage;
	}
	public void setPathImage(String pathImage) {
		this.pathImage = pathImage;
	}
	public String getNameEsperienza() {
		return nameEsperienza;
	}
	public void setNameEsperienza(String nameEsperienza) {
		this.nameEsperienza = nameEsperienza;
	}
	public boolean isVertical() {
		return isVertical;
	}
	public void setVertical(boolean isVertical) {
		this.isVertical = isVertical;
	}
	public boolean isMainImage() {
		return isMainImage;
	}
	public void setMainImage(boolean isMainImage) {
		this.isMainImage = isMainImage;
	}
	public boolean isExperienceImage() {
		return isExperienceImage;
	}
	public void setExperienceImage(boolean isExperienceImage) {
		this.isExperienceImage = isExperienceImage;
	}
	public boolean isDemo() {
		return isDemo;
	}
	public void setDemo(boolean isDemo) {
		this.isDemo = isDemo;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public Integer getIdEsperienza() {
		return idEsperienza;
	}
	public void setIdEsperienza(Integer idEsperienza) {
		this.idEsperienza = idEsperienza;
	}
	public byte[] getPicByte() {
		return picByte;
	}
	public void setPicByte(byte[] picByte) {
		this.picByte = picByte;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date date) {
		this.data = date;
	}

}
