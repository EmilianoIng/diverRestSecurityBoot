package com.diver.main.security.model;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;

import org.springframework.format.annotation.DateTimeFormat;

public class DettagliAnagraficiDtoInput implements Serializable {
	
	private String nome;
	private String cognome;
	private String username;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataDiNascita;
	private String luogoDiNascita;
	private String residenza;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getDataDiNascita() {
		return dataDiNascita;
	}
	public void setDataDiNascita(Date dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}
	public String getLuogoNascita() {
		return luogoDiNascita;
	}
	public void setLuogoNascita(String luogoDiNascita) {
		this.luogoDiNascita = luogoDiNascita;
	}
	public String getResidenza() {
		return residenza;
	}
	public void setResidenza(String residenza) {
		this.residenza = residenza;
	}
	@Override
	public String toString() {
		return "DettagliAnagraficiDtoInput [nome=" + nome + ", cognome=" + cognome + ", username=" + username
				+ ", dataDiNascita=" + dataDiNascita + ", luogoDiNascita=" + luogoDiNascita + ", residenza=" + residenza
				+ "]";
	}
	public DettagliAnagraficiDtoInput(String nome, String cognome, String username, Date dataDiNascita,
			String luogoDiNascita, String residenza) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.dataDiNascita = dataDiNascita;
		this.luogoDiNascita = luogoDiNascita;
		this.residenza = residenza;
	}
	public DettagliAnagraficiDtoInput() {
		super();
		// TODO Auto-generated constructor stub
	}

}
