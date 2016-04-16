package com.packtpub.springmvc.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
	public void addPerson(Restaurant res) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updatePerson(Restaurant res) {
		// TODO Auto-generated method stub

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
	public User getRestaurant(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeRestaurant(int id) {
		// TODO Auto-generated method stub

	}

}
