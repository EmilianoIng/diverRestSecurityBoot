package com.diver.main.security.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Role {
	
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY )
	private Integer id;
	private String name;
	@JsonIgnore
	@ManyToMany(/*fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
                },*/
                mappedBy = "ruoli")
	private Set<User> l;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<User> getL() {
		return l;
	}
	public void setL(Set<User> l) {
		this.l = l;
	}

}
