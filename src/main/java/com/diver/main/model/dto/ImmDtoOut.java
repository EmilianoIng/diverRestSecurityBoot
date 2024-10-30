package com.diver.main.model.dto;

public class ImmDtoOut {
	
	private Integer id;
	private String nameViaggioGita;
	private String pathImage;
	private String nameEsperienza;
	private boolean isVertical;
	private boolean isMainImage;
	private boolean isExperienceImage;
	private boolean isDemo;
	private Integer idViaggio;
	private Integer idEsperienza;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNameViaggioGita() {
		return nameViaggioGita;
	}
	public void setNameViaggioGita(String nameViaggioGita) {
		this.nameViaggioGita = nameViaggioGita;
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
	public Integer getIdViaggio() {
		return idViaggio;
	}
	public void setIdViaggio(Integer idViaggio) {
		this.idViaggio = idViaggio;
	}
	public Integer getIdEsperienza() {
		return idEsperienza;
	}
	public void setIdEsperienza(Integer idEsperienza) {
		this.idEsperienza = idEsperienza;
	}
	@Override
	public String toString() {
		return "ImmDtoOut [id=" + id + ", nameViaggioGita=" + nameViaggioGita + ", pathImage=" + pathImage
				+ ", nameEsperienza=" + nameEsperienza + ", isVertical=" + isVertical + ", isMainImage=" + isMainImage
				+ "]";
	}

}