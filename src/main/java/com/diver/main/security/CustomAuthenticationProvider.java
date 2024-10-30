package com.diver.main.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.diver.main.exception.UserNotFoundException;
import com.diver.main.security.model.User;
import com.diver.main.security.model.Role;
import com.diver.main.service.SecurityInterface;
import com.diver.main.user.service.UserServiceInterface;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider
{
	
/* star test emi@Autowired
private SecurityInterface userService;*/
	
	@Autowired 
	private UserServiceInterface userService;// codice sostitutivo
	@Autowired
	private PasswordEncoder encoder;
	
@Override
public Authentication authenticate(Authentication authentication) throws AuthenticationException {
	String username = authentication.getName();
	String password = (String)authentication.getCredentials();
    
	UsernamePasswordAuthenticationToken	 auth=null; 
 User user=null;
	//try {
	System.out.println("username della chiamata rest "+username);
	System.out.println("password della chiamata rest "+(String)authentication.getCredentials());
	//password=pe.encode(password);
	System.out.println("password della chiamata rest encoded "+password);

	/*star test emi
	user = userService.loadUserByUsername(username);
		UserDetails  user = null;
	*/
// codice sostitutivo
	try {
	user = userService.doesUserExist(username);
	System.out.println("in customProvider: "+user);
	} catch (UserNotFoundException e) {
		System.out.println("utente non trovato in custom Provider");
	}
	//end  codice sostitutivo

	if (user == null || !user.getUsername().equalsIgnoreCase(username)) {
		System.out.println("username diverse!!!");
	throw new BadCredentialsException("Username not found.");
	}
	if (!encoder.matches(password,user.getPassword())) {
		System.out.println(encoder.matches("Jacopo2012!", password));
		System.out.println("password  diverse!!!");
	throw new BadCredentialsException("Wrong password.");
	}
	Set< GrantedAuthority> authorities	= new HashSet<>();
	for(Role r:user.getRuoli())
	{
		//GrantedAuthority gr=new SimpleGrantedAuthority(r.getName());
		authorities.add(new SimpleGrantedAuthority(r.getName()));
	}
	
	auth= new	UsernamePasswordAuthenticationToken(user, password,authorities);
    System.out.println("oggetto authenticate creato: "+auth);
	return auth;	
}
	@Override
	public boolean supports(Class<?> authentication) {
	return authentication.equals(
	UsernamePasswordAuthenticationToken.class);
	}
	

	}