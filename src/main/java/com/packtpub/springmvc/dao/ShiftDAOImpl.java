package com.packtpub.springmvc.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.packtpub.springmvc.model.Shift;

@Repository
public class ShiftDAOImpl implements ShiftDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public List<Shift> shiftList() {
		Session session = this.sessionFactory.getCurrentSession();
		
		List<Shift> shiftList = session.createQuery("from Shift").list();
		return shiftList;
	}
	
	
}
