package com.diver.main.model.dto;

public class VideoDtoOut {
	
	private Integer id;
	private String nomeGitaViaggio;
	private String nome;
	private String thumbnail;
	private String DescrizioneVideo;
    private String YouTubeId;
    private boolean demo;
    

	public String getNomeGitaViaggio() {
		return nomeGitaViaggio;
	}
	public void setNomeGitaViaggio(String nomeGitaViaggio) {
		this.nomeGitaViaggio = nomeGitaViaggio;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getDescrizioneVideo() {
		return DescrizioneVideo;
	}
	public void setDescrizioneVideo(String descrizioneVideo) {
		DescrizioneVideo = descrizioneVideo;
	}
	public String getYouTubeId() {
		return YouTubeId;
	}
	public void setYouTubeId(String youTubeId) {
		YouTubeId = youTubeId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public boolean isDemo() {
		return demo;
	}
	public void setDemo(boolean demo) {
		this.demo = demo;
	}
	@Override
	public String toString() {
		return "VideoDtoOut [nomeGitaViaggio=" + nomeGitaViaggio + ", nome=" + nome + ", thumbnail=" + thumbnail
				+ ", DescrizioneVideo=" + DescrizioneVideo + ", YouTubeId=" + YouTubeId + "]";
	}
}
