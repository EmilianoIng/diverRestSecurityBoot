package com.diver.main.security.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
//import javax.validation.constraints.Past;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "dettagli_anagrafici")
public class DettagliAnagrafici implements Serializable{
/*	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY )
	private Integer id;*/
	@Id
	private String username;
	private String nome;
	private String cognome;
	//@Past
	@Column(name = "data_nascita")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataDiNascita;
	private String residenza;
	@OneToOne(mappedBy = "da")
	private User user;
	@Column(name = "luogo_nascita")
	private String LuogoDiNascita;
	@CreationTimestamp
	private LocalDateTime startDate;
	
	@UpdateTimestamp
	private LocalDateTime lastUpdate;
	
	/*public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}*/
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public Date getDataDiNascita() {
		return dataDiNascita;
	}
	public void setDataDiNascita(Date dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}
	public String getResidenza() {
		return residenza;
	}
	public void setResidenza(String residenza) {
		this.residenza = residenza;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getLuogoDiNascita() {
		return LuogoDiNascita;
	}
	public void setLuogoDiNascita(String luogoDiNascita) {
		LuogoDiNascita = luogoDiNascita;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}


}
