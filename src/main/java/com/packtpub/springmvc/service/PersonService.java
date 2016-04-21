package com.packtpub.springmvc.service;

import java.util.List;

import com.packtpub.springmvc.model.Restaurant;
import com.packtpub.springmvc.model.Role;
import com.packtpub.springmvc.model.Staff;
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
	
	public Staff getStaff(String email);
	
	public void addNewStaff(Staff s);
	
	public void updateRestaurant(Restaurant r);
	
	public Role getRole(int id);
	
	public void addRestaurant(Restaurant r);
	
	public List<Staff> listStaffs();

}
