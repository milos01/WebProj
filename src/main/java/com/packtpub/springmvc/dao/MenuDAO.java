package com.packtpub.springmvc.dao;

import java.util.List;

import com.packtpub.springmvc.model.AlcoholicDrink;
import com.packtpub.springmvc.model.Appetizer;
import com.packtpub.springmvc.model.Desert;
import com.packtpub.springmvc.model.MainCourse;
import com.packtpub.springmvc.model.Menu;
import com.packtpub.springmvc.model.NonAlcoholicDrink;
import com.packtpub.springmvc.model.VineCard;

public interface MenuDAO {

	public Menu getMenu(int id);
	public void updateMenu(Menu m);
	
	public VineCard getVineCard(int id);	
	public void updateVineCard(VineCard c);
	
	public void addAlcoholicDrink(AlcoholicDrink a);
	public void AddNonAlcoholicDrink(NonAlcoholicDrink a);
	public AlcoholicDrink findAlchDrink(int id);
	public NonAlcoholicDrink findNoNAlchDrink(int id);
	public void updateAlcoholicDrink(AlcoholicDrink ad);
	public void updateNonAlcoholicDrink(NonAlcoholicDrink ad);
	
	public void addAppetizer(Appetizer a);
	public void updateAppetizer(Appetizer a);
	public List<Appetizer> listAppetizer();
	public Appetizer findAppetizer(int id);
	
	public void addDesert(Desert d);
	public void updateDesert(Desert d);
	public List<Desert> listDesert();
	public Desert findDesert(int id);
	
	public void addMainCourse(MainCourse mc);
	public void updateMainCourse(MainCourse mc);
	public List<MainCourse> listMainCourse();
	public MainCourse findMainCourse(int id);
	
	public MainCourse getRestMainCourse(String name);
	public Appetizer getRestAppetizer(String name);
	public Desert getRestDesert(String name);
	
	public AlcoholicDrink getRestAlcholic(String name);
	public NonAlcoholicDrink getNonAlcoholic(String name);
}
