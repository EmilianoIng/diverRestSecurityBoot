package com.diver.main.model.dto;


import java.util.Date;




public class EsperienzaDtoInput {
	private String nameGita;
	private String nameEsperienza;
	private String descrizione;
	private Date  data;
	public String getNameEsperienza() {
		return nameEsperienza;
	}
	public void setNameEsperienza(String nameEsperienza) {
		this.nameEsperienza = nameEsperienza;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getNameGita() {
		return nameGita;
	}
	public void setNameGita(String nameGita) {
		this.nameGita = nameGita;
	}
	@Override
	public String toString() {
		return "EsperienzaDtoInput [nameEsperienza=" + nameEsperienza + ", descrizione=" + descrizione + ", data="
				+ data + "]";
	}

}
