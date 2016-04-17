package com.packtpub.springmvc.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.packtpub.springmvc.model.User;
import com.packtpub.springmvc.model.VerificationToken;

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
	public void updatePerson(User p,User u) {
		Session session = this.sessionFactory.getCurrentSession();
		p.setFirstName(u.getFirstName());
		p.setLastName(u.getLastName());
		p.setPassword(u.getPassword());
		session.merge(p);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> listPersons() {
		Session session = this.sessionFactory.getCurrentSession();

		List<User> personsList = session.createQuery("from Person").list();
		// for(Person p : personsList){
		// }
		return personsList;
	}

	@Override
	public User getPerson(String email) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query1 = session.createQuery("FROM User u WHERE u.email = :string_email");
		query1.setParameter("string_email", email);
		List<User> userList = query1.list();
		User tk = null;
		for (User users : userList) {
			tk = users;
		}
		return tk;

	}

	@Override
	public void removePerson(int id) {
		// TODO Auto-generated method stub

	}

}
