package com.packtpub.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.packtpub.springmvc.dao.PersonDAO;
import com.packtpub.springmvc.dao.RestaurantDAO;
import com.packtpub.springmvc.dao.RoleDAO;
import com.packtpub.springmvc.dao.StaffDAO;
import com.packtpub.springmvc.dao.TokenDAO;
import com.packtpub.springmvc.model.Restaurant;
import com.packtpub.springmvc.model.Role;
import com.packtpub.springmvc.model.Staff;
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
	
	@Autowired
	private StaffDAO staffDAO;
	
	@Autowired
	private RoleDAO roleDAO;

	
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

	@Override
	@Transactional
	public Staff getStaff(String email) {
		return this.staffDAO.getStaff(email);
	}

	@Override
	@Transactional
	public void addNewStaff(Staff s) {
		this.staffDAO.addStaff(s);
		
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
	public List<Staff> listStaffs() {
		return this.staffDAO.listStaffs();
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
	public User findPerson(int id) {
		return this.personDAO.findUser(id);
	}

	@Override
	public boolean checkForFreeTables(String res_date, String res_from, String res_to) {
		// TODO Auto-generated method stub
		return false;
	}
}
