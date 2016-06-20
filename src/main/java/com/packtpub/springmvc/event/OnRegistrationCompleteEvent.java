package com.packtpub.springmvc.event;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationEvent;

import com.packtpub.springmvc.model.Reservation;
import com.packtpub.springmvc.model.User;

@SuppressWarnings("serial")
public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private User user;
    private Reservation res;
    private User logedUser;
 

    public OnRegistrationCompleteEvent(User user, Reservation res, User logedUser) {
    	super(user);
    	this.user = user;
    	this.res = res;
    	this.logedUser = logedUser;
    	
    }

    
	public User getLogedUser() {
		return logedUser;
	}


	public void setLogedUser(User logedUser) {
		this.logedUser = logedUser;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Reservation getRes() {
		return res;
	}


	public void setRes(Reservation res) {
		this.res = res;
	}

    //
}
