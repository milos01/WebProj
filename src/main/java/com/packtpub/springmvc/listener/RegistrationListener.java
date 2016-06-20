package com.packtpub.springmvc.listener;

import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.packtpub.springmvc.event.OnRegistrationCompleteEvent;
import com.packtpub.springmvc.model.Reservation;
import com.packtpub.springmvc.model.Restaurant;
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
        final User u = event.getUser();
        final Reservation res = event.getRes();
        final User logedUser = event.getLogedUser();
//        final String token = UUID.randomUUID().toString();
//        personService.createVerificationTokenForUser(user, token);
        final SimpleMailMessage email = constructEmailMessage(event, u, res, logedUser);
        System.out.println(email);
        mailSender.send(email);
    }

    //

    private final SimpleMailMessage constructEmailMessage(final OnRegistrationCompleteEvent event, final User u, final Reservation res, User logedUser) {
    	final String recipientAddress = u.getEmail();
        final Restaurant restaurant = res.getRes_restaurant();
        final String subject = "Food order";
        
//        final String confirmationUrl = event.getAppUrl() + "/regitrationConfirm.html?token=" + token;
        final String message = "User " + logedUser.getFirstName() + " " + logedUser.getLastName() + " has invited you on some"
        		+ " tasteful meal at " + restaurant.getName() + ".\n Click on the link to choose food for You: http://localhost:8080/springmvc/restaurant/" + restaurant.getId() +"/reservation/"+res.getId()+"/user/"+logedUser.getId()+"/from/"+u.getId()+"/order";
        //System.out.println(recipientAddress + "," + subject + "," + confirmationUrl + "," + message);
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setTo("milosa942@gmail.com");
        email.setSubject(subject);
        email.setText(message);
        email.setFrom("milos942@gmail.com");
        return email;
        
    }

}
