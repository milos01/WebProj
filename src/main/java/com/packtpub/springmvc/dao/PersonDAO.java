package com.packtpub.springmvc.dao;

import java.util.List;

import com.packtpub.springmvc.model.User;

public interface PersonDAO {
	public void addPerson(User p);

	public void updatePerson(User p, User u);

	public List<User> listPersons();

	public User getPerson(String email);

	public void removePerson(int id);
}
