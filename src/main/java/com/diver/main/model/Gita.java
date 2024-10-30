package com.diver.main.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "gite")
public class Gita {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	
	@OneToOne
	@JoinColumn(name = "id_experience", referencedColumnName = "id")
	private Esperienza esperienze;
	
	@CreationTimestamp
	private LocalDateTime startDate;
	@UpdateTimestamp
	private LocalDateTime lastUpdate;
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
	public Esperienza getExperience() {
		return esperienze;
	}
	public void setExperience(Esperienza experience) {
		this.esperienze = experience;
	}
	public Boolean getDemo() {
		return demo;
	}
	public void setDemo(Boolean demo) {
		this.demo = demo;
	}
	@Override
	public String toString() {
		return "Gita [id=" + id + ", nome=" + nome + ", esperienze=" + esperienze + ", startDate=" + startDate
				+ ", lastUpdate=" + lastUpdate + ", demo=" + demo + "]";
	}

}
