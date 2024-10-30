package com.diver.main.security.model;

import org.springframework.context.ApplicationEvent;

public class OnRegistrationCompleteEvent extends ApplicationEvent {
    private String appUrl;
   // private Locale locale;
    private User user;

    public OnRegistrationCompleteEvent(
      User user,/* Locale locale,*/ String appUrl) {
        super(user);
        
        this.user = user;
       // this.locale = locale;
        this.appUrl = appUrl;
    }

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
    
    // standard getters and setters
}