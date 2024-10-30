package com.diver.main.user.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.diver.main.exception.UserAlredyExist;
import com.diver.main.exception.UserNotFoundException;
import com.diver.main.security.model.CambioPwdDtoInput;
import com.diver.main.security.model.DettagliAnagrafici;
import com.diver.main.security.model.DettagliAnagraficiDtoInput;
import com.diver.main.security.model.User;
import com.diver.main.security.model.UserDetailsImpl;
import com.diver.main.security.model.VerificationToken;
import com.diver.main.user.repository.AnagraficaDaoInterface;
import com.diver.main.user.repository.TokenDaoInterface;
import com.diver.main.user.repository.UserDaoInterface;
import com.diver.main.user.repository.UserRoleDaoInterface;
import com.diver.main.utility.Utility;

@Service
public class UserServiceImpl implements UserServiceInterface {

	@Autowired
	UserDaoInterface uDao;
	@Autowired
	TokenDaoInterface tokenDao;
	@Autowired
	PasswordEncoder pe;
	@Autowired
	UserRoleDaoInterface uRoleDao;
	@Autowired
	AnagraficaDaoInterface anagraficaDao;
	@Override
	@Transactional
	public User addUser(User u) {
		// TODO Auto-generated method stub
			 //return	uDao.save(u).getUsername();
		System.out.println("password register encoded: "+pe.encode(u.getPassword()));
		if(!isUserPresent(u.getUsername()))
		{u.setPassword(pe.encode(u.getPassword()));
			uDao.addUser(u);
			System.out.println("utente inserito!!! "+u);
			uRoleDao.addUserRole(u);
		return	uDao.findByUsername(u.getUsername());}
		else
			throw new UserAlredyExist("utente già presente");
			
	}
	@Override
	public boolean isUserPresent(String email) {
		// TODO Auto-generated method stub
		if(uDao.findByUsername(email)==null)
		return false;
		else
			return true;
	}

	
	@Override
	    public void createVerificationTokenForUser(final User user, final String token) {
	        final VerificationToken myToken = new VerificationToken(token, user);
	        tokenDao.addToken(myToken);
	        //tokenRepository.save(myToken);
	    }
	@Override
	public VerificationToken getVerificationToken(String searchToken) {
		// TODO Auto-generated method stub
		return tokenDao.getVerificationTokenFromToken(searchToken);
	}
	@Override
	public User updateUser(User u) {
		// TODO Auto-generated method stub
		return uDao.update(u);
	}
	
	
	@Override
	@Transactional
	public void deleteUser(User u) {
		// TODO Auto-generated method stub
		System.out.println("elimina token e user");
		tokenDao.deleteToken(u.getToken());
		uDao.deleteUser(u);
		
	}
	
	// test from book
	
	@Override
	public User doesUserExist(String email) throws UserNotFoundException {
		User u=new User();
	
		 u =  uDao.findByUsername(email);
	System.out.println("utente recuperato: "+u);
	if(u==null)
		throw
		new UserNotFoundException("Username errata");
	
	return u;
	}
	@Override
	@Transactional
	public void addAnagraficaUser(DettagliAnagraficiDtoInput dett) {
		DettagliAnagrafici dettA=new DettagliAnagrafici();
		User u=uDao.findByUsername(dett.getUsername());
		System.out.println("byquery "+u);
		dettA=mappingAnagraficaDtoToEntity(dett);
		dettA.setUser(u);
		anagraficaDao.insertDettagli(dettA);
		
	}
	
private DettagliAnagrafici mappingAnagraficaDtoToEntity(DettagliAnagraficiDtoInput dett) {
		DettagliAnagrafici dettA=new DettagliAnagrafici();
		dettA.setUsername(dett.getUsername());
		dettA.setCognome(dett.getCognome());
		dettA.setDataDiNascita(dett.getDataDiNascita());
		dettA.setLuogoDiNascita(dett.getLuogoNascita());
		dettA.setNome(dett.getNome());
		dettA.setResidenza(dett.getResidenza());
		return dettA;
	}

private DettagliAnagraficiDtoInput mappingAnagraficaEntityToDto(DettagliAnagrafici dett) {
	DettagliAnagraficiDtoInput dettA=new DettagliAnagraficiDtoInput();
	dettA.setUsername(dett.getUsername());
	dettA.setCognome(dett.getCognome());
	dettA.setDataDiNascita(dett.getDataDiNascita());
	dettA.setLuogoNascita(dett.getLuogoDiNascita());
	dettA.setNome(dett.getNome());
	dettA.setResidenza(dett.getResidenza());
	return dettA;
}
@Override
public DettagliAnagraficiDtoInput retrieveAnagraficaUser(String user)  {
	// TODO Auto-generated method stub
	try {
	return mappingAnagraficaEntityToDto(anagraficaDao.retrieveDettagli(user));
	}
	catch(Exception e) {
		return null;
	}
}

@Override
@Transactional
public void updateAnagraficaUser(DettagliAnagraficiDtoInput dett) {
	anagraficaDao.updateDettagli(this.mappingAnagraficaDtoToEntity(dett));
	
}

@Override
@Transactional
public boolean checkUserDisabledExpireToken(VerificationToken token) {
	// TODO Auto-generated method stub
	 Calendar cal = Calendar.getInstance();
	    if ((token.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
	 	   
	    	tokenDao.deleteToken(token);
	    	uDao.deleteUser(token.getU());
	    	return true;
	     
	    } 
	return false;
}
@Override
public void changePwdUser(CambioPwdDtoInput cred) {
	// TODO Auto-generated method stub
	User u=this.uDao.findByUsername(cred.getUsername());
	String oldPwd;
	if(u!=null)
		oldPwd=this.pe.encode(cred.getOldPassord());
	else throw new BadCredentialsException("Username non presente");
	if(!pe.matches(cred.getOldPassord(), u.getPassword()))
		throw new BadCredentialsException("Password errata!!!");
	u.setPassword(this.pe.encode(cred.getPassword()));
	this.updateUser(u);
	
}
@Override
public String resetPwd(String user) throws BadCredentialsException {
	// TODO Auto-generated method stub
	User u=this.uDao.findByUsername(user);
	String s;
	if(u==null)
		throw new BadCredentialsException("Prima di procedere,settare correttamente il campo Email soprastante");
		s=Utility.alphaNumericString();
		u.setPassword(this.pe.encode(s));
		this.uDao.update(u);
	return s;
}
	
	
}
