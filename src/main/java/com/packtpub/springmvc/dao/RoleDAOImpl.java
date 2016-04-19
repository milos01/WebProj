package com.packtpub.springmvc.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.packtpub.springmvc.model.Role;

@Repository
public class RoleDAOImpl implements RoleDAO {

	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	@Override
	public Role getRole(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query1 =  session.createQuery("FROM Role u WHERE u.id =:intId");
		query1.setParameter("intId",id);
		List<Role> RolefList = query1.list();
		
		Role s = null;
		for (Role st:RolefList){
			s = st;
		}
		return s;
	}

}
