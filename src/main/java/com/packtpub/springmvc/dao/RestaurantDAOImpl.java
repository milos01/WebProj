package com.packtpub.springmvc.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.packtpub.springmvc.model.Recension;
import com.packtpub.springmvc.model.Restaurant;

import com.packtpub.springmvc.model.User;


@Repository
public class RestaurantDAOImpl implements RestaurantDAO {

	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void addRestaurant(Restaurant res) {
		Session session = this.sessionFactory.getCurrentSession();
		System.out.println("uso");
		session.persist(res);

	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Restaurant> listRestaurants() {
		System.out.println("aki ca");
		Session session = this.sessionFactory.getCurrentSession();
        System.out.println("aki caki");
		List<Restaurant> restaurantList = session.createQuery("from Restaurant").list();
        return restaurantList;
	}

	@Override
	public Restaurant getRestaurant(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query1 =  session.createQuery("FROM Restaurant rs WHERE rs.id = :id");
		query1.setParameter("id",id);
		List<Restaurant> restaurantList = query1.list();
		Restaurant rs = null;
		for ( Restaurant restaurant: restaurantList ) {
		   rs = restaurant;
		}
		return rs; 
	}

	@Override
	public void removeRestaurant(int id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Query query3 = session.createQuery("DELETE FROM Event ev WHERE ev.restaurant.id = :iddd ");
		query3.setParameter("iddd",id);
		query3.executeUpdate();
		Query query2 = session.createQuery("DELETE FROM Recension rs WHERE rs.restaurant.id = :idd ");
		query2.setParameter("idd",id);
		query2.executeUpdate();
		Query query1 = session.createQuery("DELETE FROM Restaurant rc WHERE rc.id = :id ");
		query1.setParameter("id",id);
		query1.executeUpdate();

	}

	@Override
	public void updateRestaurant(Restaurant res) {
		Session session = this.sessionFactory.getCurrentSession();
		session.merge(res);
	}

	@Override
	public List<Restaurant> getUsersRestaurants(int id) {
		Session session = this.sessionFactory.getCurrentSession();
        System.out.println("aki caki");
        Query query1 = session.createQuery("from Restaurant rs ");
//		query1.setParameter("id",id);
		List<Restaurant> restaurantList = query1.list();
        return restaurantList;
	}

	@Override
	public void addRecension(Recension rec) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();

		session.persist(rec);
	}

	@Override 
	public void removeRecension(int resid) {
		System.err.println(resid);
		Session session = this.sessionFactory.getCurrentSession();
		Query query1 = session.createQuery("DELETE FROM Recension rc WHERE rc.id = :id ");
		query1.setParameter("id",resid);
		query1.executeUpdate();
		// TODO Auto-generated method stub
		
	}

}
