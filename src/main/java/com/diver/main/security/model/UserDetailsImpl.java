package com.diver.main.security.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class UserDetailsImpl implements UserDetails{

	private User userlogIn;
	private String userRuolo="";

	public String getUserRuolo() {
		return userRuolo;
	}

	public void setUserRuolo() { // ritorna l'authoriti di rango più elevato
		//this.userRuolo = userRuolo;
		
	/*	for(Ruoli role:userlogIn.getRuoli()) {
		
			switch(role.getRuolo()) {
			case "ROLE_ADMIN":{
				this.userRuolo="ADMIN";
				break;}
				case "ROLE_USER":{
					if(!this.userRuolo.equals("ADMIN"))
						this.userRuolo="USER";
					break;
				}
				case "ROLE_GUEST":{
					if(!this.userRuolo.equals("ADMIN")&&!this.userRuolo.equals("USER"))
						this.userRuolo="ROLE_GUEST";
					break;
				}
				default:{
					
					this.userRuolo="ROLE_ANONYMOUS";
					break;
				}
			
			}
		}*/
	}

	public User getUserlogIn() {
		return userlogIn;
	}

	public void setUserlogIn(User userlogIn) {
		this.userlogIn = userlogIn;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		Set<GrantedAuthority> authorities=new HashSet<>();
	/*	for(Ruoli role:userlogIn.getRuoli()) {
			authorities.add(new SimpleGrantedAuthority(role.getRuolo()));
		}*/
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return userlogIn.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userlogIn.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
