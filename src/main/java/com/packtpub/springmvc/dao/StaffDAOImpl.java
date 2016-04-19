package com.packtpub.springmvc.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.packtpub.springmvc.model.Staff;

@Repository
public class StaffDAOImpl implements StaffDAO {

	
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public void addStaff(Staff s) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(s);

	}

	@Override
	public void updateStaff(Staff a) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Staff> listStaffs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Staff getStaff(String email) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query1 =  session.createQuery("FROM Staff u WHERE u.email =:string_email");
		query1.setParameter("string_email",email);
		List<Staff> staffList = query1.list();
		System.out.println(staffList.size());
		
		Staff s = null;
		for (Staff st:staffList){
			s = st;
		}
		return s;
	}

	@Override
	public void removeStaff(int id) {
		// TODO Auto-generated method stub

	}

}
