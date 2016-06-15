package com.packtpub.springmvc.dao;

import java.util.List;

import com.packtpub.springmvc.model.FoodItem;
import com.packtpub.springmvc.model.FoodListItem;
import com.packtpub.springmvc.model.GrocaryList;
import com.packtpub.springmvc.model.Offer;

public interface BidderDAO {

	public void addGroceryList(GrocaryList gl);
	public void addFoodItem(FoodItem fi);
	public void addFoodListItem(FoodListItem fli);
	
	public FoodItem findFoodItem(String name);
	public List<FoodListItem> findFoodList(int id);
	public List<FoodItem> GetAllFoodItems();
	
	public GrocaryList findGrocery(int id);
	
	public void createOffer(Offer of);
	
	public Offer getOffer(int grocery_id,int user_id);
	
	public List<Offer> getOffers(int grocery_id);
	public void updateOffer(Offer of);
	
	public List<Offer> getAllUserOffers(int id_user);
	
	public Offer findOffer(int idOf);
	
}
