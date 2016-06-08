package com.packtpub.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.packtpub.springmvc.dao.MenuDAO;
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
import com.packtpub.springmvc.model.MainCourse;
import com.packtpub.springmvc.model.Menu;
import com.packtpub.springmvc.model.NonAlcoholicDrink;
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
	public List<TablePosition> tablePositions() {
		// TODO Auto-generated method stub
		return null;
	}

}
