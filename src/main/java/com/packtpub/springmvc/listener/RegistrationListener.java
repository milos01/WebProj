package com.packtpub.springmvc.listener;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.packtpub.springmvc.event.OnRegistrationCompleteEvent;
import com.packtpub.springmvc.model.User;
import com.packtpub.springmvc.service.PersonService;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
    
	private PersonService personService;

	@Autowired(required = true)
	@Qualifier(value = "personService")
	public void setPersonService(PersonService ps) {
		this.personService = ps;
	}
 
    private MessageSource messages;

    @Autowired
    private JavaMailSender mailSender;

    
    private Environment env;

    // API

    @Override
    public void onApplicationEvent(final OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(final OnRegistrationCompleteEvent event) {
        final User user = event.getUser();
        final String token = UUID.randomUUID().toString();
        personService.createVerificationTokenForUser(user, token);
        final SimpleMailMessage email = constructEmailMessage(event, user, token);
        System.out.println(email);
        mailSender.send(email);
    }

    //

    private final SimpleMailMessage constructEmailMessage(final OnRegistrationCompleteEvent event, final User user, final String token) {
        final String recipientAddress = user.getEmail();
        final String subject = "Registration Confirmation";
        final String confirmationUrl = event.getAppUrl() + "/regitrationConfirm.html?token=" + token;
        final String message = "Click on the link to activate your account.";
        //System.out.println(recipientAddress + "," + subject + "," + confirmationUrl + "," + message);
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + " \r\n" + confirmationUrl);
        email.setFrom("milos94@gmail.com");
        return email;
        
    }

}
