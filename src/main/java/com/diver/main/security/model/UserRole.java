package com.diver.main.security.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@IdClass(UserRoleId.class)
@Table(name="user_role")
public class UserRole {
	
	@Id
	@Column(name = "user_id")
	private Integer idUser;
	@Id
	@Column(name = "role_id")
	private Integer idRole;
	@CreationTimestamp
	private LocalDateTime startDate;
	
	@UpdateTimestamp
	private LocalDateTime lastUpdate;
	public Integer getIdUser() {
		return idUser;
	}
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	public Integer getIdRole() {
		return idRole;
	}
	public void setIdRole(Integer idRole) {
		this.idRole = idRole;
	}

}
