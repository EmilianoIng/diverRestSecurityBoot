package com.diver.main;


import java.security.Principal;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.diver.main.security.model.User;
import com.diver.main.user.service.UserServiceInterface;

@RestController
@SpringBootApplication
public class DiverRestSecurityBootApplication/* implements CommandLineRunner*/{

	@Autowired
	UserServiceInterface uService;
	
	public static void main(String[] args) {
		SpringApplication.run(DiverRestSecurityBootApplication.class, args);
	}

@RequestMapping("/user")
public Principal user(Principal user) {
	  System.out.println("user principal: "+user);
	 
  return user;
}
	/*@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		User emi=new User();
		emi.setUsername("triviato83@gmail.com");
		emi.setPassword("Jacopo2012");
		System.out.println("utente creato "+emi);
		uService.addUser(emi);
	}*/

}
