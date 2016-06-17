package com.packtpub.springmvc.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.packtpub.springmvc.model.Reon;
import com.packtpub.springmvc.model.Restaurant;
import com.packtpub.springmvc.model.Shift;
import com.packtpub.springmvc.model.Shift_schedule;
import com.packtpub.springmvc.model.Staff;

@Repository
public class ShiftDAOImpl implements ShiftDAO {

	@Autowired
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

	@Override
	public void addNewShiftForStaff(Shift_schedule s) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(s);
		
	}

	@Override
	public void addNewRestShift(Shift sh) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(sh);
	}

}
