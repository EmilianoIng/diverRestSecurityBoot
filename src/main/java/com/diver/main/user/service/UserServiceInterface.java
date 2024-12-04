package com.diver.main.user.service;



import com.diver.main.exception.UserNotFoundException;
import com.diver.main.security.model.CambioPwdDtoInput;
import com.diver.main.security.model.DettagliAnagraficiDtoInput;
import com.diver.main.security.model.User;
import com.diver.main.security.model.VerificationToken;

public interface UserServiceInterface {
	
	public User addUser(User u);
	public User updateUser(User u);
	public boolean isUserPresent(String email);
	public void createVerificationTokenForUser(User user, String token);
	public VerificationToken getVerificationToken(String searchToken);
	public void deleteUser(User u);
	User doesUserExist(String email) throws UserNotFoundException;
	public void addAnagraficaUser(DettagliAnagraficiDtoInput dett);
	public DettagliAnagraficiDtoInput retrieveAnagraficaUser(String user);
	public void updateAnagraficaUser(DettagliAnagraficiDtoInput dett);
	public boolean checkUserDisabledExpireToken(VerificationToken token);
	public void changePwdUser(CambioPwdDtoInput cred);
	public String resetPwd(String user);

}
