package com.packtpub.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.packtpub.springmvc.dao.BidderDAO;
import com.packtpub.springmvc.dao.MenuDAO;
import com.packtpub.springmvc.dao.OrderDAO;
import com.packtpub.springmvc.dao.PersonDAO;
import com.packtpub.springmvc.dao.RestaurantDAO;
import com.packtpub.springmvc.dao.RoleDAO;
import com.packtpub.springmvc.dao.ShiftDAO;
import com.packtpub.springmvc.dao.StaffDAO;
import com.packtpub.springmvc.dao.Table_scheduleDAO;
import com.packtpub.springmvc.dao.TokenDAO;
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
import com.packtpub.springmvc.model.Reon;
import com.packtpub.springmvc.model.ReonTypes;
import com.packtpub.springmvc.model.Order;
import com.packtpub.springmvc.model.Reservation;
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

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonDAO personDAO;
	
	@Autowired
	private TokenDAO tokenDAO;
	
	@Autowired
	private RestaurantDAO restaurantDAO;
	
	@Autowired
	private StaffDAO staffDAO;
	
	@Autowired
	private RoleDAO roleDAO;
	
	@Autowired
	private Table_scheduleDAO tableDAO;

	@Autowired
	private ShiftDAO shiftDAO;
	
	@Autowired
	private MenuDAO menuDAO;
	
	@Autowired
	private BidderDAO bidderDAO;
	
	@Autowired 
	private OrderDAO orderDAO;
	
	@Override
	@Transactional
	public void addPerson(User p) {
		this.personDAO.addPerson(p);
	}

	@Override
	@Transactional
	public void updatePerson(User p, User u) {
		personDAO.updatePerson(p, u);

	}

	@Override
	@Transactional
	public List<User> listPersons() {
		return this.personDAO.listPersons();
	}

	@Override
	@Transactional
	public User getPerson(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void removePerson(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public User loginUser(String email, String password) {
		if(this.personDAO.getPerson(email) == null){
			return null;
		}
		
		return this.personDAO.getPerson(email);
	}

	@Override
	@Transactional
	public VerificationToken getVerificationToken(String token) {
		return this.tokenDAO.findByToken(token);
	}

	@Override
	@Transactional
	public void createVerificationTokenForUser(User user, String token) {
		final VerificationToken myToken = new VerificationToken(token, user);
		tokenDAO.saveVerificationTokenForUser(myToken);
	}
	
	@Override
	@Transactional
	public void updateVerificationToken(VerificationToken token) {
		tokenDAO.updateVerificationToken(token);
	}

	@Override
	@Transactional
	public List<Restaurant> listRestaurants() {
		return this.restaurantDAO.listRestaurants();
	}

	@Override
	@Transactional
	public Restaurant getRestaurant(int id) {
		return this.restaurantDAO.getRestaurant(id);
	}

	@Override
	@Transactional
	public Staff getStaff(String email) {
		return this.staffDAO.getStaff(email);
	}

	@Override
	@Transactional
	public void addNewStaff(Staff s) {
		this.staffDAO.addStaff(s);
		
	}

	@Override
	@Transactional
	public void updateRestaurant(Restaurant r) {
		this.restaurantDAO.updateRestaurant(r);
		
	}

	@Override
	@Transactional
	public Role getRole(int id) {
		return this.roleDAO.getRole(id);
	}
	
	@Override
	@Transactional
	public void addRestaurant(Restaurant r){
		System.out.println("usao sam");
		this.restaurantDAO.addRestaurant(r);
	}

	@Override
	@Transactional
	public List<Staff> listStaffs() {
		return this.staffDAO.listStaffs();
	}

	@Override
	@Transactional
	public User findUserByName(String fname, String lname) {
		return this.personDAO.findUserByName(fname, lname);
	}

	@Override
	@Transactional
	public void updatePerson(User p) {
		this.personDAO.updatePerson(p);
	}

	@Override
	@Transactional
	public boolean findUserByEmail(String email) {
		return this.personDAO.getPersonByEmail(email);
	}

	@Override
	@Transactional
	public User findPerson(int id) {
		return this.personDAO.findUser(id);
	}

	@Override
	@Transactional
	public List<Table_schedule> checkForFreeTables(String res_date, int res_from, int res_to, int peopleNum) {
		return this.tableDAO.table_schedule_list(res_from, res_to, peopleNum);
	}

	@Override
	@Transactional
	public List<TableOne> allTables(int id) {
		return this.tableDAO.allTables(id);
	}

	@Override
	@Transactional
	public List<Shift> shiftList() {
		return this.shiftDAO.shiftList();
	}

	@Override
	@Transactional
	public void addNewStaffShift(Shift_schedule sc) {
		this.shiftDAO.addNewShiftForStaff(sc);
		
	}

	@Override
	@Transactional
	public void refreshShift(Staff s) {
		this.staffDAO.refreshShift(s);
		
	}

	@Override
	@Transactional
	public List<MainCourse> listMainCour() {
		return this.menuDAO.listMainCourse();
	}

	@Override
	@Transactional
	public Menu getMenu(int id) {
		
		return this.menuDAO.getMenu(id);
	}

	@Override
	@Transactional
	public void addMainCourse(MainCourse mc) {
		this.menuDAO.addMainCourse(mc);	
	}

	@Override
	@Transactional
	public void addDesert(Desert d) {
		this.menuDAO.addDesert(d);	
	}

	@Override
	@Transactional
	public void addAppetizer(Appetizer a) {
		this.menuDAO.addAppetizer(a);
	}

	@Override
	@Transactional
	public void updateAppetizer(Appetizer a) {
		this.menuDAO.updateAppetizer(a);
		
	}

	@Override
	@Transactional
	public void updateDesert(Desert d) {
		this.menuDAO.updateDesert(d);
		
	}

	@Override
	@Transactional
	public void updateMainCourse(MainCourse mc) {
		this.menuDAO.updateMainCourse(mc);
		
	}

	@Override
	@Transactional
	public void updateMenu(Menu m) {
		this.menuDAO.updateMenu(m);
		
	}

	@Override
	@Transactional
	public VineCard getVineCard(int id) {
		return this.menuDAO.getVineCard(id);
	}

	@Override
	@Transactional
	public void updateVineCard(VineCard c) {
		this.menuDAO.updateVineCard(c);
		
	}

	@Override
	@Transactional
	public void addAlcoholicDrink(AlcoholicDrink a) {
		this.menuDAO.addAlcoholicDrink(a);
		
	}

	@Override
	@Transactional
	public void AddNonAlcoholicDrink(NonAlcoholicDrink a) {
		this.menuDAO.AddNonAlcoholicDrink(a);
		
	}

	@Override
	@Transactional
	public AlcoholicDrink findAlchDrink(int id) {
		return this.menuDAO.findAlchDrink(id);
	}

	@Override
	@Transactional
	public NonAlcoholicDrink findNoNAlchDrink(int id) {
		return this.menuDAO.findNoNAlchDrink(id);
	}

	@Override
	@Transactional
	public Appetizer findAppetizer(int id) {
		return this.menuDAO.findAppetizer(id);
	}

	@Override
	@Transactional
	public Desert findDesert(int id) {
		return this.menuDAO.findDesert(id);
	}

	@Override
	@Transactional
	public MainCourse findMainCourse(int id) {
		return this.menuDAO.findMainCourse(id);
	}

	@Override
	@Transactional
	public void updateAlcoholicDrink(AlcoholicDrink ad) {
		this.menuDAO.updateAlcoholicDrink(ad);
		
	}

	@Override
	@Transactional
	public void updateNonAlcoholicDrink(NonAlcoholicDrink ad) {
		this.menuDAO.updateNonAlcoholicDrink(ad);
	}

	@Override
	@Transactional
	public void updateTablePosition(TablePosition tp) {
		this.tableDAO.updateTablePosition(tp);
		
	}

	@Override
	@Transactional
	public void addNeWTablePosition(TablePosition tp) {
		this.tableDAO.addNeWTablePosition(tp);
		
	}

	@Override
	@Transactional
	public void addNewTable(TableOne to) {
		this.tableDAO.addNewTable(to);
		
	}

	@Override
	@Transactional
	public List<TablePosition> tablePositions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void removeTalbePosition(TablePosition tp) {
		this.tableDAO.removeTalbePosition(tp);
		
	}

	@Override
	@Transactional
	public void removeTable(TableOne to) {
		this.tableDAO.removeTable(to);
		
	}

	@Override
	@Transactional
	public TableOne findTable(int id) {
		return this.tableDAO.findTable(id);
	}

	@Override
	@Transactional
	public TablePosition findTablePosition(int id) {
		return this.tableDAO.findTablePosition(id);
	}

	@Override
	@Transactional
	public void removeTableSchedule(int id) {
		this.tableDAO.removeTableSchedule(id);
		
	}

	@Override
	@Transactional
	public void addGroceryList(GrocaryList gl) {
		this.bidderDAO.addGroceryList(gl);
		
	}

	@Override
	@Transactional
	public void addFoodItem(FoodItem fi) {
		this.bidderDAO.addFoodItem(fi);
		
	}

	@Override
	@Transactional
	public void addFoodListItem(FoodListItem fli) {
		this.bidderDAO.addFoodListItem(fli);
		
	}

	@Override
	@Transactional
	public FoodItem findFoodItem(String name) {
		return this.bidderDAO.findFoodItem(name);
	}

	@Override
	@Transactional
	public List<FoodListItem> findFoodList(int id) {
		return this.bidderDAO.findFoodList(id);
	}

	@Override
	@Transactional
	public List<FoodItem> GetAllFoodItems() {
		return this.bidderDAO.GetAllFoodItems();
	}

	@Override
	@Transactional
	public GrocaryList findGrocery(int id) {
		return this.bidderDAO.findGrocery(id);
	}

	@Override
	@Transactional
	public void createOffer(Offer of) {
		this.bidderDAO.createOffer(of);
	}
	@Override
	@Transactional
	public boolean addTableSchedule(Table_schedule ts) {
		boolean work = this.tableDAO.addTableSchedule(ts);
		return work;
	}

	@Override
	@Transactional
	public Offer getOffer(int grocery_id, int user_id) {
		return this.bidderDAO.getOffer(grocery_id, user_id);
	}
	@Override
	@Transactional
	public TableOne findTableOn(int id) {
		return this.tableDAO.findTableOne(id);
	}

	@Override
	@Transactional
	public void updateOffer(Offer of) {
		this.bidderDAO.updateOffer(of);
	}

	@Override
	@Transactional
	public List<Offer> getAllUserOffers(int id_user) {
		return this.bidderDAO.getAllUserOffers(id_user);
	}

	@Override
	@Transactional
	public Offer findOffer(int idOf) {
		return this.bidderDAO.findOffer(idOf);
	}

	@Override
	@Transactional
	public List<Offer> getOffers(int grocery_id) {
		return this.bidderDAO.getOffers(grocery_id);
	}
	@Override
	@Transactional
	public boolean addReservations(Reservation res) {
		// TODO Auto-generated method stub
		return this.tableDAO.addReservarion(res);
	}

	@Override
	@Transactional
	public void addNewRestShift(Shift sh) {
		this.shiftDAO.addNewRestShift(sh);
	}
	@Override
	@Transactional
	public Reservation findReservation(int id) {
		
		return this.restaurantDAO.findReservation(id);
	}

	@Override
	@Transactional
	public void addNewReonType(ReonTypes rt) {
			this.restaurantDAO.addNewReonType(rt);
	}

	@Override
	@Transactional
	public List<ReonTypes> getAllReaonTypes() {
		return this.restaurantDAO.getAllReaonTypes();
	}

	@Override
	@Transactional
	public void refresType(ReonTypes rt) {
		this.restaurantDAO.referesType(rt);
	}

	@Override
	@Transactional
	public ReonTypes findReonTypes(int id) {
		return this.restaurantDAO.findReonTypes(id);
	}

	@Override
	@Transactional
	public void addNewReonToRest(Reon r) {
		this.restaurantDAO.addNewReonToRest(r);
	}

	@Override
	@Transactional
	public MainCourse getRestMainCourse(String name) {
		return this.menuDAO.getRestMainCourse(name);
	}

	@Override
	@Transactional
	public Appetizer getRestAppetizer(String name) {
		return this.menuDAO.getRestAppetizer(name);
	}

	@Override
	@Transactional
	public Desert getRestDesert(String name) {
		return this.menuDAO.getRestDesert(name);
	}

	@Override
	@Transactional
	public AlcoholicDrink getRestAlcholic(String name) {
		return this.menuDAO.getRestAlcholic(name);
	}

	@Override
	@Transactional
	public NonAlcoholicDrink getNonAlcoholic(String name) {
		return this.menuDAO.getNonAlcoholic(name);
	}
	@Override
	@Transactional
	public boolean addOrder(Order ord) {
		
		return this.orderDAO.addOrder(ord);

	}

}
