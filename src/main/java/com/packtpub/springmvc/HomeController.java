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

import com.packtpub.springmvc.model.Appetizer;
import com.packtpub.springmvc.model.CalendarJSONShow;
import com.packtpub.springmvc.model.Desert;
import com.packtpub.springmvc.model.Food;
import com.packtpub.springmvc.model.MainCourse;
import com.packtpub.springmvc.model.Menu;
import com.packtpub.springmvc.model.Reon;
import com.packtpub.springmvc.model.Restaurant;
import com.packtpub.springmvc.model.Role;
import com.packtpub.springmvc.model.Shift;
import com.packtpub.springmvc.model.Shift_schedule;
import com.packtpub.springmvc.model.Staff;
import com.packtpub.springmvc.model.User;
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
			for (Shift ss : shiftsRest) {
				System.out.println(ss.getId() + " " + ss.getShift_entry() + " " + ss.getEnd_shift());
				tempShift.add(ss);
			}
			for (Staff st : temp) {

				if (st.getRole().getId() != 5) {
					staffList.add(st);
				}
				// System.out.println(st.getFirstName());
			}
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
		}
		this.personService.refreshShift(stf);
		return "redirect:/home";
	}
	
	@RequestMapping(value = "/newDish", method = RequestMethod.POST)
	public String addNewFood(@ModelAttribute("food") Food rest,@RequestParam("restID") String restID){
		Menu m = this.personService.getMenu(Integer.parseInt(restID));
		
		if (rest.getType().equals("Appetizer")){
			Appetizer a = new Appetizer();
			a.setName(rest.getName());
			a.setPrice(rest.getPrice());
			a.setPicture(rest.getPicture());
			this.personService.addAppetizer(a);
			m.getAppetizer().add(a);
		}
		else if(rest.getType().equals("Desert")){
			Desert a = new Desert();
			a.setName(rest.getName());
			a.setPrice(rest.getPrice());
			a.setPicture(rest.getPicture());
			this.personService.addDesert(a);
			m.getDesert().add(a);
		}
		else if(rest.getType().equals("MainCourse")){
			MainCourse a = new MainCourse();
			a.setName(rest.getName());
			a.setPrice(rest.getPrice());
			a.setPicture(rest.getPicture());
			this.personService.addMainCourse(a);
			m.getMainCourse().add(a);
		}
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
