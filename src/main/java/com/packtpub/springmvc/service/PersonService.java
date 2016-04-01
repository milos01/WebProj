package com.packtpub.springmvc.service;

import java.util.List;

import com.packtpub.springmvc.model.User;
import com.packtpub.springmvc.model.VerificationToken;

public interface PersonService {
	
	public void createVerificationTokenForUser(User user, String token);
	
	public void addPerson(User p);

	public void updatePerson(User p);
	
	public VerificationToken getVerificationToken(String token);

	public List<User> listPersons();

	public User getPersonById(int id);

	public void removePerson(int id);
	
	public User loginUser(String username, String password);

}
