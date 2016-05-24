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
	
	public void addAppetizer(Appetizer a);
	public void updateAppetizer(Appetizer a);
	public List<Appetizer> listAppetizer();
	
	public void addDesert(Desert d);
	public void updateDesert(Desert d);
	public List<Desert> listDesert();
	
	public void addMainCourse(MainCourse mc);
	public void updateMainCourse(MainCourse mc);
	public List<MainCourse> listMainCourse();
}
