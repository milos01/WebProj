package com.packtpub.springmvc.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.packtpub.springmvc.model.AlcoholicDrink;
import com.packtpub.springmvc.model.Appetizer;
import com.packtpub.springmvc.model.Desert;
import com.packtpub.springmvc.model.FoodItem;
import com.packtpub.springmvc.model.FoodListItem;
import com.packtpub.springmvc.model.GrocaryList;
import com.packtpub.springmvc.model.MainCourse;
import com.packtpub.springmvc.model.Menu;
import com.packtpub.springmvc.model.NonAlcoholicDrink;
import com.packtpub.springmvc.model.Offer;
import com.packtpub.springmvc.model.Restaurant;
import com.packtpub.springmvc.model.Role;
import com.packtpub.springmvc.model.Shift;
import com.packtpub.springmvc.model.Shift_schedule;
import com.packtpub.springmvc.model.Staff;
import com.packtpub.springmvc.model.TableOne;
import com.packtpub.springmvc.model.TablePosition;
import com.packtpub.springmvc.model.Table_schedule;
import com.packtpub.springmvc.model.User;
import com.packtpub.springmvc.model.VerificationToken;
import com.packtpub.springmvc.model.VineCard;

public interface PersonService {

	public void createVerificationTokenForUser(User user, String token);

	public void addPerson(User p);

	public void updatePerson(User p, User u);
	
	public void updatePerson(User p);

	public boolean findUserByEmail(String email);

	public VerificationToken getVerificationToken(String token);

	public List<User> listPersons();
	
	public User findPerson(int id);

	public User getPerson(String email);

	public void removePerson(int id);

	public User loginUser(String username, String password);

	public void updateVerificationToken(VerificationToken token);

	public List<Restaurant> listRestaurants();

	public Restaurant getRestaurant(int id);
	
	public Staff getStaff(String email);
	
	public void addNewStaff(Staff s);
	
	public void updateRestaurant(Restaurant r);
	
	public Role getRole(int id);
	
	public void addRestaurant(Restaurant r);
	
	public List<Staff> listStaffs();
	
	public User findUserByName(String fname, String lname);

	public List<Table_schedule> checkForFreeTables(String res_date, int res_from, int res_to, int pn);
	
	public List<TableOne> allTables(int id);

	public List<Shift> shiftList();
	
	public void addNewStaffShift(Shift_schedule sc);
	
	public void refreshShift(Staff s);
	
	public List<MainCourse> listMainCour();
	
	public Menu getMenu(int id);
	
	public void addMainCourse(MainCourse mc);
	
	public void addDesert(Desert d);
	
	public void addAppetizer(Appetizer a);
	
	public void updateAppetizer(Appetizer a);

	public void updateDesert(Desert d);
	
	public void updateMainCourse(MainCourse mc);
	
	public void updateMenu(Menu m);
	
	public VineCard getVineCard(int id);
	
	public void updateVineCard(VineCard c);
	
	public void addAlcoholicDrink(AlcoholicDrink a);
	
	public void AddNonAlcoholicDrink(NonAlcoholicDrink a);
	
	public AlcoholicDrink findAlchDrink(int id);
	
	public NonAlcoholicDrink findNoNAlchDrink(int id);
	
	public Appetizer findAppetizer(int id);

	public Desert findDesert(int id);

	public MainCourse findMainCourse(int id);
	
	public void updateAlcoholicDrink(AlcoholicDrink ad);
	
	public void updateNonAlcoholicDrink(NonAlcoholicDrink ad);
	
	public void updateTablePosition(TablePosition tp);
	
	public void addNeWTablePosition(TablePosition tp);
	
	public void addNewTable(TableOne to);
	
	public List<TablePosition> tablePositions();
	
	public void removeTalbePosition(TablePosition tp);
	
	public void removeTable(TableOne to);
	
	public TableOne findTable(int id);
	
	public TablePosition findTablePosition(int id);
	
	public void removeTableSchedule(int id);
	
	public void addGroceryList(GrocaryList gl);
	
	public void addFoodItem(FoodItem fi);
	
	public void addFoodListItem(FoodListItem fli);
	
	public FoodItem findFoodItem(String name);
	
	public List<FoodListItem> findFoodList(int id);
	
	public List<FoodItem> GetAllFoodItems();
	
	public GrocaryList findGrocery(int id);
	
	public void createOffer(Offer of);
	
	public Offer getOffer(int grocery_id,int user_id);
	
	public void updateOffer(Offer of);
	
	public List<Offer> getAllUserOffers(int id_user);
	
	public Offer findOffer(int idOf);
	
	public List<Offer> getOffers(int grocery_id);
}
