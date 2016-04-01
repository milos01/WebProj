package com.packtpub.springmvc.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.packtpub.springmvc.model.User;

@Repository
public class PersonDAOImpl implements PersonDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void addPerson(User p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(p);
	}

	@Override
	public void updatePerson(User p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.merge(p);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> listPersons() {
		Session session = this.sessionFactory.getCurrentSession();
        
		List<User> personsList = session.createQuery("from Person").list();
//        for(Person p : personsList){
//        }
        return personsList;
	}

	@Override
	public User getPersonById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removePerson(int id) {
		// TODO Auto-generated method stub

	}

}
