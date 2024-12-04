package com.diver.main.security.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PreRemove;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import jakarta.persistence.GeneratedValue;

@Entity
@Table(name="user",uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
@NamedQueries({
	@NamedQuery(name="User.findByUsername",query="select e from User e where e.username=?1 ")
	
})
@SQLDelete(sql="update user set is_deleted=true where id=?")
@Where(clause="is_deleted=false")
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY )
    private int id;
	@Column(name = "username",unique = true)
	private String username;
	private String password;
	
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    @ManyToMany(fetch = FetchType.EAGER)
	private List < Role > ruoli;
	
	@OneToOne(mappedBy = "u")
	private VerificationToken token;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "username_dettAnag",referencedColumnName = "username")
	private DettagliAnagrafici da;
	
	@Column(name = "enabled")
    private boolean enabled;
	@Column(name = "is_deleted")
	private boolean idDeleted;
	
	@CreationTimestamp
	private LocalDateTime startDate;
	
	@UpdateTimestamp
	private LocalDateTime lastUpdate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public VerificationToken getToken() {
		return token;
	}
	public void setToken(VerificationToken token) {
		this.token = token;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public List<Role> getRuoli() {
		return ruoli;
	}
	public void setRuoli(List<Role> ruoli) {
		this.ruoli = ruoli;
	}
	public DettagliAnagrafici getDa() {
		return da;
	}
	public void setDa(DettagliAnagrafici da) {
		this.da = da;
	}
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.enabled=false;
	}
	public User() {
		this.enabled=false;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + "Ruoli:"+ruoli+" ]";
	}
	
	@PreRemove()
	private void preRemove() {
		this.idDeleted=true;
	}
	
}
