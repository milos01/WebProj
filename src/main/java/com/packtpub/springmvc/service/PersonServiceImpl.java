package com.packtpub.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.packtpub.springmvc.dao.PersonDAO;
import com.packtpub.springmvc.dao.TokenDAO;
import com.packtpub.springmvc.model.User;
import com.packtpub.springmvc.model.VerificationToken;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonDAO personDAO;
	
	@Autowired
	private TokenDAO tokenDAO;

	
	@Override
	@Transactional
	public void addPerson(User p) {
		this.personDAO.addPerson(p);
	}

	@Override
	@Transactional
	public void updatePerson(User p) {
		this.personDAO.updatePerson(p);

	}

	@Override
	@Transactional
	public List<User> listPersons() {
		return this.personDAO.listPersons();
	}

	@Override
	@Transactional
	public User getPersonById(int id) {
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
	public User loginUser(String username, String password) {
		this.personDAO.getPersonById(2);
		return null;
	}

	@Override
	@Transactional
	public VerificationToken getVerificationToken(String token) {
		System.out.println("aaaaa");
		return this.tokenDAO.findByToken(token);
	}

	@Override
	@Transactional
	public void createVerificationTokenForUser(User user, String token) {
		final VerificationToken myToken = new VerificationToken(token, user);
		tokenDAO.saveVerificationTokenForUser(myToken);
	}

}
