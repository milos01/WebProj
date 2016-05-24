package com.packtpub.springmvc.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.packtpub.springmvc.model.Appetizer;
import com.packtpub.springmvc.model.Desert;
import com.packtpub.springmvc.model.MainCourse;
import com.packtpub.springmvc.model.Menu;
import com.packtpub.springmvc.model.User;

@Repository
public class MenuDAOImpl implements MenuDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void addAppetizer(Appetizer a) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(a);
		
	}

	@Override
	public void updateAppetizer(Appetizer a) {
		Session session = this.sessionFactory.getCurrentSession();
		session.merge(a);
		
		
	}

	@Override
	public List<Appetizer> listAppetizer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addDesert(Desert d) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(d);
		
	}

	@Override
	public void updateDesert(Desert d) {
		Session session = this.sessionFactory.getCurrentSession();
		session.merge(d);
		
	}

	@Override
	public List<Desert> listDesert() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addMainCourse(MainCourse mc) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(mc);
		
	}

	@Override
	public void updateMainCourse(MainCourse mc) {
		Session session = this.sessionFactory.getCurrentSession();
		session.merge(mc);
		
	}

	@Override
	public List<MainCourse> listMainCourse() {
		Session session = this.sessionFactory.getCurrentSession();
		
		List<MainCourse> list = session.createQuery("from Main_course").list();
		return list;
	}

	@Override
	public Menu getMenu(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query1 = session.createQuery("FROM Menu u WHERE u.id = :string_email");
		query1.setParameter("string_email", id);
		List<Menu> userList = query1.list();
		Menu tk = null;
		for (Menu users : userList) {
			tk = users;
		}
		return tk;
	}
}
