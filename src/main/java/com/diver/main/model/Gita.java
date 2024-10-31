package com.diver.main.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

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
	public Esperienza getExperience() {
		return esperienze;
	}
	public void setExperience(Esperienza experience) {
		this.esperienze = experience;
	}
	public Integer getDemo() {
		return demo;
	}
	public void setDemo(Integer demo) {
		this.demo = demo;
	}
	@Override
	public String toString() {
		return "Gita [id=" + id + ", nome=" + nome + ", esperienze=" + esperienze + ", startDate=" + startDate
				+ ", lastUpdate=" + lastUpdate + ", demo=" + demo + "]";
	}

}
