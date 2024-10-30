package com.diver.main.utility;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.diver.main.exception.PasswordAlredyResetException;
import com.diver.main.security.model.VerificationToken;

@Component
public class Utility {
	 @Autowired
	    private MessageSource messages;
	 @Autowired
	    private Environment env;
	 @Autowired
	    private JavaMailSender mailSender;
	 
	 public static int today;
	 
	private static Map m;
	 
	 static {
		 today=new Date().getDay();
		 m=new HashMap<String,Integer>();
	 }
	
	public static String alphaNumericString() {
	    String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    String ab = "qwertyuiopasdfghjklzxcvbnm";
	    String number = "0123456789";
	    String special = "!£$%&?^|_-";
	    Random rnd = new Random();

	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < 2; i++) {
	        sb.append(AB.charAt(rnd.nextInt(AB.length())));
	    }
	    for (int i = 0; i < 2; i++) {
	        sb.append(ab.charAt(rnd.nextInt(ab.length())));
	    }
	    for (int i = 0; i < 2; i++) {
	        sb.append(number.charAt(rnd.nextInt(number.length())));
	    }
	    for (int i = 0; i < 2; i++) {
	        sb.append(special.charAt(rnd.nextInt(special.length())));
	    }
	    return sb.toString();
	}
	
	 public void constructEmailMessage( final String username, final String resettedpwd) {
	        final String recipientAddress = username;
	        final String subject = "Password Resetted";
	        //final String confirmationUrl = "192.168.1.49:6060" + "/registrationConfirm?token=" + token;
	        final String message = messages.getMessage("message.regSuccLink", null,"Hi "+username+ " your password has been successfully reset.\r\n"
	        		+ "To log in use the new password: "+resettedpwd,Locale.ITALY /*, event.getLocale()*/);
	        final SimpleMailMessage email = new SimpleMailMessage();
	        email.setTo(recipientAddress);
	        email.setSubject(subject);
	        email.setText(message /*+ " \r\n" + confirmationUrl*/);
	        email.setFrom(env.getProperty("spring.mail.username"));
	        mailSender.send(email);
	    }
	 public void constructEmailMessage( final String username,VerificationToken token) {
		Date d=new Date();
		if(d.getDay()>this.today) {
			this.today=d.getDay();
			m.clear();
			
		}
		     Integer a=(Integer)m.get(username);
		     if(a!=null)
		    	 throw
		    	 new PasswordAlredyResetException("Password nella giornata odierna già resettata ");
		     m.put(username, this.today);
	        final String recipientAddress = username;
	        final String subject = "Password Resetted";
	        final String confirmationUrl = "192.168.1.49:6060"+"/diver" + "/confirmresetpwd?token=" + token.getToken();
	        final String message = messages.getMessage("message.regSuccLink", null,"Hi "+username+ " click on the link below to reset the password.\r\n"
	        		+ confirmationUrl,Locale.ITALY /*, event.getLocale()*/);
	        final SimpleMailMessage email = new SimpleMailMessage();
	        email.setTo(recipientAddress);
	        email.setSubject(subject);
	        email.setText(message /*+ " \r\n" + confirmationUrl*/);
	        email.setFrom(env.getProperty("spring.mail.username"));
	        mailSender.send(email);
	    }

}
