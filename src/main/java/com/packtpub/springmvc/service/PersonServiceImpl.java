package com.packtpub.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.packtpub.springmvc.dao.EventDAO;
import com.packtpub.springmvc.dao.PersonDAO;
import com.packtpub.springmvc.dao.RestaurantDAO;
import com.packtpub.springmvc.dao.RoleDAO;
import com.packtpub.springmvc.model.Event;
import com.packtpub.springmvc.model.Restaurant;
import com.packtpub.springmvc.model.Role;
import com.packtpub.springmvc.model.User;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonDAO personDAO;
	

	
	@Autowired
	private RestaurantDAO restaurantDAO;
	
	
	@Autowired
	private RoleDAO roleDAO;
	
	@Autowired
	private EventDAO eventDAO;
	
	@Override
	@Transactional
	public void addPerson(User p) {
		this.personDAO.addPerson(p);
	}

	@Override
	@Transactional
	public void updatePerson(User p, User u) {
		personDAO.updatePerson(p, u);

	}

	@Override
	@Transactional
	public List<User> listPersons() {
		return this.personDAO.listPersons();
	}

	@Override
	@Transactional
	public User getPerson(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void removePerson(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public User loginUser(String email, String password) {
		if(this.personDAO.getPerson(email) == null){
			return null;
		}
		
		return this.personDAO.getPerson(email);
	}


	@Override
	@Transactional
	public List<Restaurant> listRestaurants() {
		
		return this.restaurantDAO.listRestaurants();
	}

	@Override
	@Transactional
	public Restaurant getRestaurant(int id) {
		return this.restaurantDAO.getRestaurant(id);
	}



	@Override
	@Transactional
	public void updateRestaurant(Restaurant r) {
		this.restaurantDAO.updateRestaurant(r);
		
	}

	@Override
	@Transactional
	public Role getRole(int id) {
		return this.roleDAO.getRole(id);
	}
	
	@Override
	@Transactional
	public void addRestaurant(Restaurant r){
		System.out.println("usao sam");
		this.restaurantDAO.addRestaurant(r);
	}


	@Override
	@Transactional
	public User findUserByName(String fname, String lname) {
		return this.personDAO.findUserByName(fname, lname);
	}

	@Override
	@Transactional
	public void updatePerson(User p) {
		this.personDAO.updatePerson(p);
	}

	@Override
	@Transactional
	public boolean findUserByEmail(String email) {
		return this.personDAO.getPersonByEmail(email);
	}

	@Override
	@Transactional
	public User findPerson(int id) {
		return this.personDAO.findUser(id);
	}

	@Override
	public void createVerificationTokenForUser(User user, String token) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeTableSchedule(int id) {
		// TODO Auto-generated method stub
		
	}
	
	

	@Override
	@Transactional
	public List<Restaurant> getUsersRestaurants(int id) {
		return this.restaurantDAO.getUsersRestaurants(id);
	}

	@Override
	@Transactional
	public Restaurant findRestaurant(int resid) {
		return this.restaurantDAO.getRestaurant(resid);
	}

	@Override
	@Transactional
	public void addEvent(Event eve) {
		// TODO Auto-generated method stub
		this.eventDAO.addEvent(eve);
	}

}
