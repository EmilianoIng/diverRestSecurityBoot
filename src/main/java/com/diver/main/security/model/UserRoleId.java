package com.diver.main.security.model;

import java.io.Serializable;

public class UserRoleId implements Serializable  {
	private Integer idUser;
	private Integer idRole;
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
