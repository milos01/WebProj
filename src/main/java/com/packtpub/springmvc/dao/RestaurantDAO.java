package com.packtpub.springmvc.dao;

import java.util.List;

import com.packtpub.springmvc.model.Restaurant;

public interface RestaurantDAO {
	public void addRestaurant(Restaurant res);

	public List<Restaurant> listRestaurants();

	public Restaurant getRestaurant(int id);

	public void removeRestaurant(int id);
	
	public void updateRestaurant(Restaurant r);

	public List<Restaurant> getUsersRestaurants(int id);
	
}
