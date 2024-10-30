package com.diver.main.model.dto;

public class EspDtoOut {
	

	private String nomeViaggioGita;
	
	public String getNomeViaggioGita() {
		return nomeViaggioGita;
	}
	public void setNomeViaggioGita(String nomeViaggioGita) {
		this.nomeViaggioGita = nomeViaggioGita;
	}

	@Override
	public String toString() {
		return "EspDtoOut [ nomeViaggioGita=" + nomeViaggioGita  + "]";
	}

}
