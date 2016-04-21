package com.packtpub.springmvc.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.packtpub.springmvc.model.Restaurant;
import com.packtpub.springmvc.model.User;
import com.packtpub.springmvc.model.VerificationToken;

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

	}

	@Override
	public void updateRestaurant(Restaurant res) {
		Session session = this.sessionFactory.getCurrentSession();
		session.merge(res);
	}

}
