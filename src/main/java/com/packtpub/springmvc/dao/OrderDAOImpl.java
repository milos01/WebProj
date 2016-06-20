package com.packtpub.springmvc.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.packtpub.springmvc.model.Order;
import com.packtpub.springmvc.model.User;

@Repository
public class OrderDAOImpl implements OrderDAO{
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	

	@Override
	public boolean addOrder(Order order) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(order);
		return true;
	}

}
