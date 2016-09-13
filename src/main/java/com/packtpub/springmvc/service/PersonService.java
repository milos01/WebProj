package com.packtpub.springmvc.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.packtpub.springmvc.model.Event;
import com.packtpub.springmvc.model.Restaurant;
import com.packtpub.springmvc.model.Role;
import com.packtpub.springmvc.model.User;


public interface PersonService {

	public void createVerificationTokenForUser(User user, String token);

	public void addPerson(User p);

	public void updatePerson(User p, User u);
	
	public void updatePerson(User p);

	public boolean findUserByEmail(String email);


	public List<User> listPersons();
	
	public User findPerson(int id);

	public User getPerson(String email);

	public void removePerson(int id);

	public User loginUser(String username, String password);


	public List<Restaurant> listRestaurants();

	public Restaurant getRestaurant(int id);
	

	

	
	public void updateRestaurant(Restaurant r);
	
	public Role getRole(int id);
	
	public void addRestaurant(Restaurant r);
	
	
	public User findUserByName(String fname, String lname);

	
	public void removeTableSchedule(int id);

	public List<Restaurant> getUsersRestaurants(int id);

	public Restaurant findRestaurant(int resid);

	public void addEvent(Event eve);
	
	
}
