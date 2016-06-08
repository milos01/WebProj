package com.packtpub.springmvc;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.packtpub.springmvc.model.AlcoholicDrink;
import com.packtpub.springmvc.model.Appetizer;
import com.packtpub.springmvc.model.CalendarJSONShow;
import com.packtpub.springmvc.model.Desert;
import com.packtpub.springmvc.model.Drink;
import com.packtpub.springmvc.model.Food;
import com.packtpub.springmvc.model.MainCourse;
import com.packtpub.springmvc.model.Menu;
import com.packtpub.springmvc.model.NonAlcoholicDrink;
import com.packtpub.springmvc.model.Reon;
import com.packtpub.springmvc.model.Restaurant;
import com.packtpub.springmvc.model.Role;
import com.packtpub.springmvc.model.Shift;
import com.packtpub.springmvc.model.Shift_schedule;
import com.packtpub.springmvc.model.Staff;
import com.packtpub.springmvc.model.TableOne;
import com.packtpub.springmvc.model.TablePosition;
import com.packtpub.springmvc.model.User;
import com.packtpub.springmvc.model.VineCard;
import com.packtpub.springmvc.service.PersonService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	private PersonService personService;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired(required = true)
	@Qualifier(value = "personService")
	public void setPersonService(PersonService ps) {
		this.personService = ps;
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, HttpSession session) {
		if (null != session.getAttribute("logedUser")) {
			return "redirect:/home";
		}
		return "index";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home2(Locale locale, Model model, HttpSession session) {
		if (null == session.getAttribute("logedUser")) {
			return "redirect:/";
		}
		List<Restaurant> restaurants = this.personService.listRestaurants();
		model.addAttribute("restaurants", restaurants);

		User u = (User) session.getAttribute("logedUser");
		if (u.getRole().getRoleName().equalsIgnoreCase("Manager")) {
			Staff s = this.personService.getStaff(u.getEmail());
			Restaurant restaurant = this.personService.getRestaurant(s.getRestaurant().getId());
			Set<Shift> shiftsRest = restaurant.getShifts();

			Set<Staff> temp = restaurant.getStaff();
			List<Staff> staffList = new ArrayList<Staff>();
			List<Shift> tempShift = new ArrayList<Shift>();
			
			Set<MainCourse> a = restaurant.getMenu().getMainCourse();
			Set<Desert> dd = restaurant.getMenu().getDesert();
			Set<Appetizer> ap = restaurant.getMenu().getAppetizer();
			Set<AlcoholicDrink> ad = restaurant.getVineCard().getAlcoholicDrink();
			Set<NonAlcoholicDrink> nad = restaurant.getVineCard().getNonAlcoholicDrink();
			
			List<TableOne> tables = new ArrayList<TableOne>();
			Set<Reon>listareona = restaurant.getReons();
			for (Reon r:listareona){
				for (TableOne tb:r.getTables()){
					tables.add(tb);
				}
			}
			
			List<TablePosition> tempPosition = new ArrayList<TablePosition>();
			for (TableOne tp:tables){
				tempPosition.add(tp.getTableposition());
			}
			
			for (Shift ss : shiftsRest) {
				System.out.println(ss.getId() + " " + ss.getShift_entry() + " " + ss.getEnd_shift());
				tempShift.add(ss);
			}
			for (Staff st : temp) {

				if (st.getRole().getId() != 5) {
					staffList.add(st);
				}
			}

			model.addAttribute("tablePositions",tempPosition);
			model.addAttribute("alchDrink",ad);
			model.addAttribute("NonalchDrink",nad);
			model.addAttribute("MainCours",a);
			model.addAttribute("desert",dd);
			model.addAttribute("appetizer",ap);
			model.addAttribute("staffList", staffList);
			model.addAttribute("restaurantShifts", tempShift);
			model.addAttribute("restoran", restaurant);
			model.addAttribute("menadzer", s);
		}

		return "home";
	}

	@RequestMapping(value = "/addNewStaff", method = RequestMethod.POST)
	public String addNewStaff(@ModelAttribute("radnik") Staff staff, @RequestParam("role_id") String rol,
			@RequestParam("birth_day") String bd, @RequestParam("restaurant_id") String idR,
			RedirectAttributes redirectAttributes) {

		Role temp = this.personService.getRole(Integer.parseInt(rol));
		Restaurant temp2 = this.personService.getRestaurant(Integer.parseInt(idR));
		staff.setPicture("def.png");
		staff.setRole(temp);
		staff.setRestaurant(temp2);

		try {
			Date tempDate = getFormatedDate(bd);
			staff.setBirth_date(tempDate);
			this.personService.addNewStaff(staff);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		redirectAttributes.addFlashAttribute("newStaffAdded", "Employees successfully added");
		return "redirect:/home";
	}

	@RequestMapping(value = "/editRestaurant", method = RequestMethod.POST)
	public String editRestaurant(@ModelAttribute("restoran1") Restaurant rest, RedirectAttributes redirectAttributes) {
		this.personService.updateRestaurant(rest);
		redirectAttributes.addFlashAttribute("updatedRest", "Restaurant successfully updated");
		return "redirect:/home";
	}

	@RequestMapping(value = "/newStaffShift", method = RequestMethod.POST)
	public String addNewShiftStaff(@RequestParam("shift_entry") String naziv, @RequestParam("shift_date") String datum,
			@RequestParam("reonNumber") String reonNumb, @RequestParam("staffID") String staffID,@RequestParam("restID") String restID)
			throws ParseException {

		Shift_schedule sc = new Shift_schedule();
		sc.setShift_entry(naziv);
		Date temp = getFormatedDate(datum);
		sc.setShift_date(temp);
		Staff stf = this.personService.getStaff(staffID);
		this.personService.addNewStaffShift(sc);
		stf.getShift_schedule().add(sc);
		
		
		if (reonNumb.equals(" ")) {
			System.out.println("YES");
			this.personService.refreshShift(stf);
			return "redirect:/home";
		} else {
			int brojReona = Integer.parseInt(reonNumb);
			Restaurant r = this.personService.getRestaurant(Integer.parseInt(restID));
			Reon ren = null;
			Set<Reon> reons = r.getReons(); //this.personService.findReon(brojReona, r);
			for (Reon reon:reons){
				if (reon.getReon_num()==brojReona && reon.getRestaurant().getId()==r.getId()){
					ren = reon;
				}
			}
			System.out.println(ren.toString());
			stf.getReons().add(ren);
			this.personService.refreshShift(stf);
		}
		
		return "redirect:/home";
	}
	
	@RequestMapping(value = "/newDish", method = RequestMethod.POST)
	public String addNewFood(@ModelAttribute("food") Food rest,@RequestParam("restID") String restID,RedirectAttributes redirectAttributes){
		Restaurant r =this.personService.getRestaurant(Integer.parseInt(restID));
		Menu m = r.getMenu();
		
		if (rest.getType().equals("Appetizer")){
			Appetizer a = new Appetizer();
			a.setName(rest.getName());
			a.setPrice(rest.getPrice());
			a.setPicture(rest.getPicture());
			this.personService.addAppetizer(a);
			m.getAppetizer().add(a);
			this.personService.updateMenu(m);
		}
		else if(rest.getType().equals("Desert")){
			Desert a = new Desert();
			a.setName(rest.getName());
			a.setPrice(rest.getPrice());
			a.setPicture(rest.getPicture());
			this.personService.addDesert(a);
			m.getDesert().add(a);
			this.personService.updateMenu(m);
		}
		else if(rest.getType().equals("MainCourse")){
			MainCourse a = new MainCourse();
			a.setName(rest.getName());
			a.setPrice(rest.getPrice());
			a.setPicture(rest.getPicture());
			this.personService.addMainCourse(a);
			m.getMainCourse().add(a);
			this.personService.updateMenu(m);
		}
		redirectAttributes.addFlashAttribute("newDishAdded", "Dish successfully added");
		return "redirect:/home";
	}
	@RequestMapping(value = "/newDrink",method = RequestMethod.POST)
	public String addNewDrink(@ModelAttribute("drink") Drink rest,@RequestParam("restID") String restID,RedirectAttributes redirectAttributes){
		Restaurant r =this.personService.getRestaurant(Integer.parseInt(restID));
		VineCard vc =r.getVineCard(); //this.personService.getVineCard(Integer.parseInt(restID));
		
		if (rest.getType().equals("Alcoholic")){
			AlcoholicDrink ad = new AlcoholicDrink();
			ad.setName(rest.getName());
			ad.setPrice(rest.getPrice());
			ad.setQuantity(rest.getQuantity());
			ad.setPicture(rest.getPicture());
			this.personService.addAlcoholicDrink(ad);
			vc.getAlcoholicDrink().add(ad);
			this.personService.updateVineCard(vc);
		}
		else if (rest.getType().equals("NonAlcoholic")){
			NonAlcoholicDrink ad = new NonAlcoholicDrink();
			ad.setName(rest.getName());
			ad.setPrice(rest.getPrice());
			ad.setQuantity(rest.getQuantity());
			ad.setPicture(rest.getPicture());
			this.personService.AddNonAlcoholicDrink(ad);
			vc.getNonAlcoholicDrink().add(ad);
			this.personService.updateVineCard(vc);
		}
		redirectAttributes.addFlashAttribute("newDrinkAdded", "Drink successfully added");
		
		return "redirect:/home";
	}
	
	@RequestMapping(value = "/editDish", method = RequestMethod.POST)
	public String editFood(@ModelAttribute("foodd") Food rest,RedirectAttributes redirectAttributes){
		
		if (rest.getType().equals("Appetizer")){
			System.out.println(rest.getItemID());
			Appetizer a = this.personService.findAppetizer(rest.getItemID()); //new Appetizer();
			a.setName(rest.getName());
			a.setPrice(rest.getPrice());
			if (!rest.getPicture().equals("")){
				a.setPicture(rest.getPicture());
			}
			this.personService.updateAppetizer(a);
			
		}
		else if(rest.getType().equals("Desert")){
			Desert a = this.personService.findDesert(rest.getItemID());
			a.setName(rest.getName());
			a.setPrice(rest.getPrice());
			if (!rest.getPicture().equals("")){
				a.setPicture(rest.getPicture());
			}
			this.personService.updateDesert(a);
		}
		else if(rest.getType().equals("MainCourse")){
			MainCourse a = this.personService.findMainCourse(rest.getItemID());
			a.setName(rest.getName());
			a.setPrice(rest.getPrice());
			if (!rest.getPicture().equals("")){
				a.setPicture(rest.getPicture());
			}
			this.personService.updateMainCourse(a);
		}
		redirectAttributes.addFlashAttribute("DishUpdated", "Dish successfully updated");
		return "redirect:/home";
	}
	
	@RequestMapping(value = "/editDrink",method = RequestMethod.POST)
	public String editDrink(@ModelAttribute("drinkk") Drink rest,RedirectAttributes redirectAttributes){
		System.out.println(rest.getId() + " "+rest.getName());
		if (rest.getType().equals("Alcoholic")){
			AlcoholicDrink ad = this.personService.findAlchDrink(rest.getId());
			ad.setName(rest.getName());
			ad.setPrice(rest.getPrice());
			ad.setQuantity(rest.getQuantity());
			if (!rest.getPicture().equals("")){
				ad.setPicture(rest.getPicture());
			}
			this.personService.updateAlcoholicDrink(ad);
		}
		else if (rest.getType().equals("NonAlcoholic")){
			NonAlcoholicDrink ad = this.personService.findNoNAlchDrink(rest.getId());
			ad.setName(rest.getName());
			ad.setPrice(rest.getPrice());
			ad.setQuantity(rest.getQuantity());
			if (!rest.getPicture().equals("")){
				ad.setPicture(rest.getPicture());
			}
			this.personService.updateNonAlcoholicDrink(ad);
		}
		redirectAttributes.addFlashAttribute("DrinkUpdated", "Drink successfully updated");
		return "redirect:/home";
	}
	
	public Date getFormatedDate(String date) throws ParseException {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date parsed = format.parse(date);
		Date sql = new Date(parsed.getTime());
		return sql;
	}
	
	public CalendarJSONShow getJSONFormat(Shift_schedule smena){
		CalendarJSONShow cs = new CalendarJSONShow();
		cs.setTitle(smena.getShift_entry());
		cs.setStart(smena.getShift_date());
		return cs;
	}

}
