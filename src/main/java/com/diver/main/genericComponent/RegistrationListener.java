package com.diver.main.genericComponent;

import java.util.Locale;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import com.diver.main.security.model.OnRegistrationCompleteEvent;
import com.diver.main.security.model.User;
import com.diver.main.user.service.UserServiceInterface;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
	
    @Autowired
    private UserServiceInterface service;

    @Autowired
    private MessageSource messages;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Environment env;

    // API

    @Override
    public void onApplicationEvent(final OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(final OnRegistrationCompleteEvent event) {
        final User user = event.getUser();
        final String token = UUID.randomUUID().toString();
        service.createVerificationTokenForUser(user, token);

        final SimpleMailMessage email = constructEmailMessage(event, user, token);
        mailSender.send(email);
    }

    //

    private SimpleMailMessage constructEmailMessage(final OnRegistrationCompleteEvent event, final User user, final String token) {
        final String recipientAddress = user.getUsername();
        final String subject = "Registration Confirmation";
        final String confirmationUrl = /*event.getAppUrl()*/"192.168.1.49:6060" + "/registrationConfirm?token=" + token;
        final String message = messages.getMessage("message.regSuccLink", null, "You registered successfully. To confirm your registration, please click on the below link.",Locale.ITALY /*, event.getLocale()*/);
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + " \r\n" + confirmationUrl);
        email.setFrom(env.getProperty("spring.mail.username"));
        return email;
    }
    

}