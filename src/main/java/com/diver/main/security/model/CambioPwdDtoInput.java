package com.diver.main.security.model;

public class CambioPwdDtoInput {
	
	private String username;
	private String password;
	private String oldPassord;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getOldPassord() {
		return oldPassord;
	}
	public void setOldPassord(String oldPassord) {
		this.oldPassord = oldPassord;
	}
	@Override
	public String toString() {
		return "CambioPwdDtoInput [username=" + username + ", password=" + password + ", oldPassord=" + oldPassord
				+ "]";
	}
	public CambioPwdDtoInput(String username, String password, String oldPassord) {
		super();
		this.username = username;
		this.password = password;
		this.oldPassord = oldPassord;
	}
	public CambioPwdDtoInput() {
		super();
		// TODO Auto-generated constructor stub
	}

}
