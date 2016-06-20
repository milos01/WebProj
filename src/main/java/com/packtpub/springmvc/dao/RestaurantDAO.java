package com.packtpub.springmvc.dao;

import java.util.List;

import com.packtpub.springmvc.model.Reon;
import com.packtpub.springmvc.model.ReonTypes;
import com.packtpub.springmvc.model.Reservation;
import com.packtpub.springmvc.model.Restaurant;

public interface RestaurantDAO {
	public void addRestaurant(Restaurant res);

	public List<Restaurant> listRestaurants();

	public Restaurant getRestaurant(int id);

	public void removeRestaurant(int id);
	
	public void updateRestaurant(Restaurant r);
	
	public void addNewReonType(ReonTypes rt);
	
	public List<ReonTypes> getAllReaonTypes();

	public Reservation findReservation(int id);

	public void referesType(ReonTypes rt);
	
	public ReonTypes findReonTypes(int id);
	
	public void addNewReonToRest(Reon r);
	
}
