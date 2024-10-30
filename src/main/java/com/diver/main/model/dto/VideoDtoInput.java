package com.diver.main.model.dto;

import org.springframework.web.multipart.MultipartFile;

public class VideoDtoInput {
	
	private String name;// nome viaggio gita
	private String videoId; // youtubeId
	private String specificPlace; // nome video
	private String descrizione; // da aggiungere nel dto di out lato view
	//private MultipartFile fileImage;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVideoId() {
		return videoId;
	}
	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}
	public String getSpecificPlace() {
		return specificPlace;
	}
	public void setSpecificPlace(String specificPlace) {
		this.specificPlace = specificPlace;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public VideoDtoInput(String name,String descrizione,String specificPlace, String videoId ) {
		super();
		this.name = name;
		this.videoId = videoId;
		this.specificPlace = specificPlace;
		this.descrizione = descrizione;
	}
	public VideoDtoInput() {
		super();
		
	}
	/*public MultipartFile getFileImage() {
		return fileImage;
	}
	public void setFileImage(MultipartFile fileImage) {
		this.fileImage = fileImage;
	}*/
	@Override
	public String toString() {
		return "VideoDtoInput [name=" + name + ", videoId=" + videoId + ", specificPlace=" + specificPlace
				+ ", descrizione=" + descrizione +/* ", fileImage=" + fileImage + */"]";
	}

}
