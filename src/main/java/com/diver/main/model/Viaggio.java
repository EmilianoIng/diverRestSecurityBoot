package com.diver.main.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "viaggi")
public class Viaggio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	@CreationTimestamp
	private LocalDateTime startDate;
	@UpdateTimestamp
	private LocalDateTime lastUpdate;
	@OneToMany(mappedBy ="viaggio" )
	private List <Esperienza> esperienze;
	private Boolean demo;
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
	public List<Esperienza> getEsperienze() {
		return esperienze;
	}
	public void setEsperienze(List<Esperienza> esperienze) {
		this.esperienze = esperienze;
	}
	public Boolean getDemo() {
		return demo;
	}
	public void setDemo(Boolean demo) {
		this.demo = demo;
	}
	@Override
	public String toString() {
		return "Viaggio [id=" + id + ", nome=" + nome + "]";
	}
	

}
