package com.packtpub.springmvc.service;

import java.util.List;

import com.packtpub.springmvc.model.Restaurant;
import com.packtpub.springmvc.model.User;
import com.packtpub.springmvc.model.VerificationToken;

public interface PersonService {

	public void createVerificationTokenForUser(User user, String token);

	public void addPerson(User p);

	public void updatePerson(User p, User u);

	public VerificationToken getVerificationToken(String token);

	public List<User> listPersons();

	public User getPerson(String email);

	public void removePerson(int id);

	public User loginUser(String username, String password);

	public void updateVerificationToken(VerificationToken token);

	public List<Restaurant> listRestaurants();

	public Restaurant getRestaurant(int id);

}
