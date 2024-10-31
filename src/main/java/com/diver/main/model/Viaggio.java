package com.diver.main.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

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
	private Integer demo;
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
	public Integer getDemo() {
		return demo;
	}
	public void setDemo(Integer demo) {
		this.demo = demo;
	}
	@Override
	public String toString() {
		return "Viaggio [id=" + id + ", nome=" + nome + "]";
	}
	

}
