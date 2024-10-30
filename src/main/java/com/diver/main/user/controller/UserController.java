package com.diver.main.user.controller;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.diver.main.security.model.User;
import com.diver.main.security.model.VerificationToken;
import com.diver.main.user.service.UserServiceInterface;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "Location",exposedHeaders = "*")

public class UserController {

	@Autowired
	UserServiceInterface uService;
	
	@GetMapping(path ="/registrationConfirm")
	public String confirmRegistration
	  (/*WebRequest request,*/  @RequestParam("token") String token) {
	 
	 //   Locale locale = request.getLocale();
	    System.out.println("token da request: "+token);
	    VerificationToken verificationToken = uService.getVerificationToken(token);
	    if (verificationToken == null) {
	       // String message = messages.getMessage("auth.message.invalidToken", null, locale);
	      //  model.addAttribute("message", message);
	      //  return "redirect:/badUser.html?lang="/* + locale.getLanguage()*/;
	    }
	    
	    User user = verificationToken.getU();
	    Calendar cal = Calendar.getInstance();
	    if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
	 	   
	    	uService.deleteUser(user);
	    	System.out.println("token scaduto!!");
	        return "errorLoginExpires"/* + locale.getLanguage()*/;
	    } 
		System.out.println("token valido!!");
	    user.setEnabled(true); 
	   // service.saveRegisteredUser(user);
	    uService.updateUser(user);
	    return "login";
	  //  return "redirect:/login.html?lang="/* + request.getLocale().getLanguage()*/; 
	}
	
	@GetMapping(path ="/test")
	public String test() {
		return "login";
	}
}
