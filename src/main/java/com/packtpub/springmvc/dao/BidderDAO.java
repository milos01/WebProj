package com.packtpub.springmvc.dao;

import java.util.List;

import com.packtpub.springmvc.model.FoodItem;
import com.packtpub.springmvc.model.FoodListItem;
import com.packtpub.springmvc.model.GrocaryList;

public interface BidderDAO {

	public void addGroceryList(GrocaryList gl);
	public void addFoodItem(FoodItem fi);
	public void addFoodListItem(FoodListItem fli);
	
	public FoodItem findFoodItem(String name);
	public List<FoodListItem> findFoodList(int id);
	public List<FoodItem> GetAllFoodItems();
	
	public GrocaryList findGrocery(int id);
}
