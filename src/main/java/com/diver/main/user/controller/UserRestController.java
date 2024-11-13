package com.diver.main.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.diver.main.exception.UserAlredyExist;
import com.diver.main.exception.UserNotFoundException;
import com.diver.main.security.CustomAuthenticationProvider;
import com.diver.main.security.model.CambioPwdDtoInput;
import com.diver.main.security.model.DettagliAnagraficiDtoInput;
import com.diver.main.security.model.OnRegistrationCompleteEvent;
import com.diver.main.security.model.User;

import com.diver.main.user.service.UserServiceInterface;
import com.diver.main.utility.Utility;


import java.net.URI;


import jakarta.annotation.security.RolesAllowed;
import jakarta.servlet.http.HttpServletRequest;



import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(path = "/diver")
@CrossOrigin(origins = "*", allowedHeaders = "Location",exposedHeaders = "*")

public class UserRestController {

	@Autowired
	UserServiceInterface uService;
	
	@Autowired
	ApplicationEventPublisher eventPublisher;
	
	@Autowired
	CustomAuthenticationProvider authenticationProvider;
	
	@Autowired
	Utility util;
	
	@PostMapping(path = "/register")
	public  ResponseEntity<User> registerUser(@RequestBody User u,final HttpServletRequest request)   {
		/*User n=new User();
		n.setPassword(u.getPassword());
		n.setUsername(u.getUsername());*/
		System.out.println("in register!!! "+u.getPassword());
		URI location;
		User n=new User();
		try {
			n=uService.addUser(u);	
		}
		
		catch(UserAlredyExist uExc) {
			System.out.println("getappUrl "+getAppUrl(request));
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	//	if(n!=null)
		//{
			 eventPublisher.publishEvent(new OnRegistrationCompleteEvent(n/*, request.getLocale()*/, getAppUrl(request)));
			location=ServletUriComponentsBuilder.fromCurrentRequest().replacePath("diver/user").path("/{idUser}").buildAndExpand(n.getId()).toUri();
	
			return  ResponseEntity.created(location).build();
		//}
		//else
			//throw new UserNotFoundException("impossibile creare l'utente");
		
		
	}
	
	// login custom
	@PostMapping(value="/loginCustom")
	public ResponseEntity<String> /*ExecutionStatus Authentication*/ processLogin(@RequestBody User reqUser,HttpServletRequest request) {
		System.out.println("in logincustom");
	Authentication authentication = null;
	UsernamePasswordAuthenticationToken token = new
	UsernamePasswordAuthenticationToken(reqUser.getUsername(),
	reqUser.getPassword());
	try {
	
	authentication =
	this.authenticationProvider.authenticate(token);
	//
	// Store the authentication object in
	// SecurityContextHolder
	SecurityContextHolder.getContext().setAuthentication(authentication);
	User user = (User) authentication.getPrincipal();
	System.out.println("in logincustom principal"+user);
	
	if(!user.isEnabled() && uService.checkUserDisabledExpireToken(user.getToken()))
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Possibiltà di confermare la registrazione scaduta.Ripetere l'operazione di registrazione");
	else if(!user.isEnabled() && !uService.checkUserDisabledExpireToken(user.getToken()))
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Prima di poter effettuare il Login, inviare conferma alla registrazione.");
	/*return new ExecutionStatus("USER_LOGIN_SUCCESSFUL", "Login
	Successful!", user);*/
	user.setPassword(null);
	} catch (BadCredentialsException e) {
		System.out.println("badException !!!");
		System.out.println(e.getMessage());
	/*return new ExecutionStatus("USER_LOGIN_UNSUCCESSFUL", "Username
	or password is incorrect. Please try again!");*/
	
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(""+e.getMessage());
	}
	
	 return ResponseEntity.status(HttpStatus.OK).build();
	}
	/*@GetMapping(value="/logout")
	public String logout (HttpServletRequest request,
	HttpServletResponse response) {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	String resp=""+auth.getName();
	if (auth != null){
	new SecurityContextLogoutHandler().logout(request, response, auth);
	}
	Authentication auth2 = SecurityContextHolder.getContext().getAuthentication();
	return ""+resp+" Logged out! "+auth2;
	}*/
	
	@PostMapping(value = "/anagrafiche")
	@RolesAllowed({"User","Admin"})
	public ResponseEntity<String> insertAnagrafica(@RequestBody DettagliAnagraficiDtoInput anag){
		System.out.println("dettaglio: "+anag);
		uService.addAnagraficaUser(anag);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping(value = "/anagrafiche/{username}")
	@RolesAllowed({"User","Admin"})
	public DettagliAnagraficiDtoInput retrieveAnagrafica(@PathVariable String username){
		/*System.out.println("dettaglio: "+anag);
		uService.addAnagraficaUser(anag);*/
		
		return uService.retrieveAnagraficaUser(username);
	}
	
	@PatchMapping(value = "/anagrafiche")
	@RolesAllowed({"User","Admin"})
	public ResponseEntity<String> updateAnagrafica(@RequestBody DettagliAnagraficiDtoInput anag){
		/*System.out.println("dettaglio: "+anag);
		uService.addAnagraficaUser(anag);*/
		uService.updateAnagraficaUser(anag);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	
	@PatchMapping(value = "/changepwd")
	@RolesAllowed({"User","Admin"})
	public ResponseEntity<String> updatePwd(@RequestBody CambioPwdDtoInput cred){
		/*System.out.println("dettaglio: "+anag);
		uService.addAnagraficaUser(anag);*/
		try {
			System.out.println("cred "+cred);
			uService.changePwdUser(cred);
		}
	catch(BadCredentialsException ex) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(""+ex.getMessage());
	}
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@GetMapping(value = "/confirmresetpwd")
	public ResponseEntity<String> resetPwd(@RequestParam(name = "token") String token){
		/*System.out.println("dettaglio: "+anag);
		uService.addAnagraficaUser(anag);*/
		try {
			String newPwd;
			
		String username=uService.getVerificationToken(token).getU().getUsername();
        if(username!=null) {  
		newPwd=uService.resetPwd(username);
          System.out.println("new password!!! "+newPwd);
          util.constructEmailMessage(username, newPwd);}
		}
	catch(BadCredentialsException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(""+ex.getMessage());
	}
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@GetMapping(value = "/resetpwd")	
	public ResponseEntity<String> requestResetPwd(@RequestParam(name = "mail",required = true) String username){
		/*System.out.println("dettaglio: "+anag);
		uService.addAnagraficaUser(anag);*/
		try {
			
			User u=this.uService.doesUserExist(username);
			if(u!=null)
          util.constructEmailMessage(username, u.getToken());
		}
	catch(UserNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(""+ex.getMessage());
	}
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	
	@GetMapping
	public String testController() {
		return "Controller funziona!!!";
	}
	  private String getAppUrl(HttpServletRequest request) {
		 
	        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	    }
}
