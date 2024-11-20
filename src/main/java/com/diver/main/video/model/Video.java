package com.diver.main.video.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.diver.main.model.Esperienza;

@Entity
@Table(name = "video")
public class Video {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	@Column(name = "youtube_id")
	private String youtube;
	private String descrizione;
	private Integer duration;
	private String thumbnail;
	private boolean demo;
	/*@Column(name = "id_esperienza")
	private Integer foreignKey;*/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_esperienza")
	private Esperienza esp;
	
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
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	
	public String getYoutube() {
		return youtube;
	}
	public void setYoutube(String youtube) {
		this.youtube = youtube;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	/*public Integer getForeignKey() {
		return foreignKey;
	}
	public void setForeignKey(Integer foreignKey) {
		this.foreignKey = foreignKey;
	}*/
	public Esperienza getEsp() {
		return esp;
	}
	public void setEsp(Esperienza esp) {
		this.esp = esp;
	}
	public boolean isDemo() {
		return demo;
	}
	public void setDemo(boolean demo) {
		this.demo = demo;
	}
	@Override
	public String toString() {
		return "Video [id=" + id + ", nome=" + nome + ", youTubeId=" + youtube + ", descrizione=" + descrizione
				+ ", thumbnail=" + thumbnail + "]";
	}

}
