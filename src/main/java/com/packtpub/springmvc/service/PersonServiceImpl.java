package com.packtpub.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.packtpub.springmvc.dao.PersonDAO;
import com.packtpub.springmvc.dao.RestaurantDAO;
import com.packtpub.springmvc.dao.TokenDAO;
import com.packtpub.springmvc.model.Restaurant;
import com.packtpub.springmvc.model.User;
import com.packtpub.springmvc.model.VerificationToken;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonDAO personDAO;
	
	@Autowired
	private TokenDAO tokenDAO;
	
	@Autowired
	private RestaurantDAO restaurantDAO;

	
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
	public VerificationToken getVerificationToken(String token) {
		return this.tokenDAO.findByToken(token);
	}

	@Override
	@Transactional
	public void createVerificationTokenForUser(User user, String token) {
		final VerificationToken myToken = new VerificationToken(token, user);
		tokenDAO.saveVerificationTokenForUser(myToken);
	}
	
	@Override
	@Transactional
	public void updateVerificationToken(VerificationToken token) {
		tokenDAO.updateVerificationToken(token);
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
}
