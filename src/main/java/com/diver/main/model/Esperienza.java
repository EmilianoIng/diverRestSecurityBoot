package com.diver.main.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.diver.main.video.model.Video;

@Entity
@Table( name = "esperienze")
public class Esperienza {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String descrizione;
	@CreationTimestamp
	private LocalDateTime startDate;
	@UpdateTimestamp
	private LocalDateTime lastUpdate;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name="id_viaggio")
	private Viaggio viaggio;
	
	@OneToMany(mappedBy = "esp")
	private List<Video> video;
	
	@OneToMany(mappedBy="espImm")
	private List<Immagine> immagini;
	
	@OneToOne(mappedBy = "esperienze")
	private Gita gita;
	
	private Date data;

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

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	

	public List<Video> getVideo() {
		return video;
	}

	public void setVideo(List<Video> video) {
		this.video = video;
	}

	public Viaggio getViaggio() {
		return viaggio;
	}

	public void setViaggio(Viaggio viaggio) {
		this.viaggio = viaggio;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Gita getGita() {
		return gita;
	}

	public void setGita(Gita gita) {
		this.gita = gita;
	}

	@Override
	public String toString() {
		return "Esperienza [id=" + id + ", nome=" + nome + ", descrizione=" + descrizione + "]";
	}

}
