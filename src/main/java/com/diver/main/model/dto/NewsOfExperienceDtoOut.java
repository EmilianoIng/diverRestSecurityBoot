package com.diver.main.model.dto;

import java.util.List;

public class NewsOfExperienceDtoOut {
	private List<NewsExperienceDtoOut> newsVideo;
	private List<NewsExperienceDtoOut> newsGite;
	private List<NewsExperienceDtoOut> newsViaggi;
	public List<NewsExperienceDtoOut> getNewsVideo() {
		return newsVideo;
	}
	public void setNewsVideo(List<NewsExperienceDtoOut> newsVideo) {
		this.newsVideo = newsVideo;
	}
	public List<NewsExperienceDtoOut> getNewsGite() {
		return newsGite;
	}
	public void setNewsGite(List<NewsExperienceDtoOut> newsGite) {
		this.newsGite = newsGite;
	}
	public List<NewsExperienceDtoOut> getNewsViaggi() {
		return newsViaggi;
	}
	public void setNewsViaggi(List<NewsExperienceDtoOut> newsViaggi) {
		this.newsViaggi = newsViaggi;
	}

}
