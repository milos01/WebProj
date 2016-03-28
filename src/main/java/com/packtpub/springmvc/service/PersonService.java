package com.packtpub.springmvc.service;

import java.util.List;

import com.packtpub.springmvc.model.Person;

public interface PersonService {
	public void addPerson(Person p);

	public void updatePerson(Person p);

	public List<Person> listPersons();

	public Person getPersonById(int id);

	public void removePerson(int id);
	
	public Person loginUser(String username, String password);

}
