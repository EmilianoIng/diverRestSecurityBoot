package com.diver.main.model.dto;

import java.util.Date;

public class NewsExperienceDtoOut {
	private String nomeViaggioGita;
	private String nomeEsperienza;
	private String nomeVideo;
	private Integer videoId;
	private Integer esperienzaId;
	private String thumbnail;
	private boolean isVertical;
	private boolean isMainImage;
	private boolean isExperienceImage;
	private boolean isDemo;
	private String youTubeId;
	private Date lastUpdate;
	private byte[] picByte;
	public String getNomeViaggioGita() {
		return nomeViaggioGita;
	}
	public void setNomeViaggioGita(String nomeViaggioGita) {
		this.nomeViaggioGita = nomeViaggioGita;
	}
	public String getNomeEsperienza() {
		return nomeEsperienza;
	}
	public void setNomeEsperienza(String nomeEsperienza) {
		this.nomeEsperienza = nomeEsperienza;
	}
	public Integer getVideoId() {
		return videoId;
	}
	public void setVideoId(Integer videoId) {
		this.videoId = videoId;
	}
	public Integer getEsperienzaId() {
		return esperienzaId;
	}
	public void setEsperienzaId(Integer esperienzaId) {
		this.esperienzaId = esperienzaId;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
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
	public String getYouTubeId() {
		return youTubeId;
	}
	public void setYouTubeId(String youTubeId) {
		this.youTubeId = youTubeId;
	}
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public String getNomeVideo() {
		return nomeVideo;
	}
	public void setNomeVideo(String nomeVideo) {
		this.nomeVideo = nomeVideo;
	}
	public byte[] getPicByte() {
		return picByte;
	}
	public void setPicByte(byte[] picByte) {
		this.picByte = picByte;
	}
	@Override
	public String toString() {
		return "NewsVideoDtoOut [nomeViaggioGita=" + nomeViaggioGita + ", nomeEsperienza=" + nomeEsperienza
				+ ", videoId=" + videoId + ", esperienzaId=" + esperienzaId + ", thumbnail=" + thumbnail
				+ ", isVertical=" + isVertical + ", isMainImage=" + isMainImage + ", isExperienceImage="
				+ isExperienceImage + ", isDemo=" + isDemo + ", youTubeId=" + youTubeId + ", lastUpdate=" + lastUpdate
				+ "]";
	}
	

}
