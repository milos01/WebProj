package com.packtpub.springmvc.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.packtpub.springmvc.dao.PersonDAO;
import com.packtpub.springmvc.model.Person;

@Service
public class PersonServiceImpl implements PersonService {

	private PersonDAO personDAO;

	public void setPersonDAO(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	@Override
	@Transactional
	public void addPerson(Person p) {
		this.personDAO.addPerson(p);
	}

	@Override
	@Transactional
	public void updatePerson(Person p) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public List<Person> listPersons() {
		return this.personDAO.listPersons();
	}

	@Override
	@Transactional
	public Person getPersonById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void removePerson(int id) {
		// TODO Auto-generated method stub

	}

}
