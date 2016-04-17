package com.packtpub.springmvc.dao;

import java.util.List;

import com.packtpub.springmvc.model.Restaurant;
import com.packtpub.springmvc.model.User;

public interface RestaurantDAO {
	public void addPerson(Restaurant res);

	public void updatePerson(Restaurant res);

	public List<Restaurant> listRestaurants();

	public Restaurant getRestaurant(int id);

	public void removeRestaurant(int id);

}
