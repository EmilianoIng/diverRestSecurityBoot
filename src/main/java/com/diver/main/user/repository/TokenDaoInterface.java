package com.diver.main.user.repository;


import com.diver.main.security.model.VerificationToken;

public interface TokenDaoInterface {

	public VerificationToken addToken(VerificationToken u);
	public VerificationToken getVerificationTokenFromToken(String u);
	public void deleteToken(VerificationToken token);
}
