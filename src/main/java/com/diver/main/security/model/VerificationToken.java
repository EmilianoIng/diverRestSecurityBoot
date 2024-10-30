package com.diver.main.security.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="verificationtoken")
@NamedQueries({
	@NamedQuery(name="VerificationToken.findByToken",query="select v from VerificationToken v where v.token=?1 ")
	
})
public class VerificationToken {

	private static final int EXPIRATION = 60 * 24;

	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	private int id;

	@OneToOne
	@JoinColumn(name = "id_user", referencedColumnName = "id")
	@JsonIgnore
	private User u;

	private String token;
	@Column(name = "expiryDate")
	private Date expiryDate;

	public VerificationToken(String token2, User user) {
		// TODO Auto-generated constructor stub
		this.token=token2;
		this.u=user;
		this.expiryDate=calculateExpiryDate(EXPIRATION);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getU() {
		return u;
	}

	public void setU(User u) {
		this.u = u;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	private Date calculateExpiryDate(final int expiryTimeInMinutes) {
		final Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(new Date().getTime());
		cal.add(Calendar.MINUTE, expiryTimeInMinutes);
		return new Date(cal.getTime().getTime());
	}

	public void updateToken(final String token) {
		this.token = token;
		this.expiryDate = calculateExpiryDate(EXPIRATION);
	}

	@Override
	public String toString() {
		return "VerificationToken [id=" + id + ", token=" + token + ", expiryDate=" + expiryDate + "]";
	}
	
	public VerificationToken() {
		this.expiryDate=calculateExpiryDate(EXPIRATION);
	}

}
