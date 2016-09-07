package com.packtpub.springmvc;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.tools.ToolProvider;

import org.hibernate.dialect.lock.PessimisticEntityLockException;
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


import com.packtpub.springmvc.model.Restaurant;
import com.packtpub.springmvc.model.Role;

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
//		if (null == session.getAttribute("logedUser")) {
//			return "redirect:/";
//		}
//		List<Restaurant> restaurants = this.personService.listRestaurants();
//		User user = (User)session.getAttribute("logedUser");
//		model.addAttribute("restaurants", restaurants);
//		
//		Calendar cal = Calendar.getInstance();
//		
//		model.addAttribute("currTime",cal.getTimeInMillis());
//		User u = (User) session.getAttribute("logedUser");
//		
//		
//		
//		if (u.getRole().getRoleName().equalsIgnoreCase("Manager")) {
//			Staff s = this.personService.getStaff(u.getEmail());
//			Restaurant restaurant = this.personService.getRestaurant(s.getRestaurant().getId());
//	
//			List<Staff> staffList = new ArrayList<Staff>();
//			List<Shift> tempShift = new ArrayList<Shift>();
//	
//			
//			List<TableOne> tables = new ArrayList<TableOne>();
//		
//			
//			List<ReonTypes> tipoviReona = new ArrayList<ReonTypes>();
//			
//			
//			
//			
//			List<FoodListItem> ponude = new ArrayList<FoodListItem>();
//			
//			
//			List<GrocaryList> grocList = new ArrayList<GrocaryList>();
//			
//			Collections.sort(grocList, Collections.reverseOrder(new Comparator<GrocaryList>() {
//			    public int compare(GrocaryList m1, GrocaryList m2) {
//			        return m1.getGLfrom().compareTo(m2.getGLfrom());
//			    }
//			}));
//			
//			List<Offer> offerForGroc = new ArrayList<Offer>();
//			
//			List<ReonTypes> sviTipovi = this.personService.getAllReaonTypes();
//			List<FoodItem> listaItema = this.personService.GetAllFoodItems();
//			
//			model.addAttribute("tables",tables);
//			model.addAttribute("offerForGroc",offerForGroc);
//			model.addAttribute("grocList",grocList);
//			model.addAttribute("listaSvihItema",listaItema);
//			model.addAttribute("ponude",ponude);
//			
//			model.addAttribute("staffList", staffList);
//			model.addAttribute("restaurantShifts", tempShift);
//			model.addAttribute("restoran", restaurant);
//			model.addAttribute("menadzer", s);
//			model.addAttribute("tipoviReona", tipoviReona);
//			model.addAttribute("sviTipovi", sviTipovi);
//		}

		return "home";
	}

//	@RequestMapping(value = "/addNewStaff", method = RequestMethod.POST)
//	public String addNewStaff(@ModelAttribute("radnik") Staff staff, @RequestParam("role_id") String rol,
//			@RequestParam("birth_day") String bd, @RequestParam("restaurant_id") String idR,
//			RedirectAttributes redirectAttributes) {
//
//		Role temp = this.personService.getRole(Integer.parseInt(rol));
//		Restaurant temp2 = this.personService.getRestaurant(Integer.parseInt(idR));
//		staff.setPicture("def.png");
//		staff.setRole(temp);
//		staff.setRestaurant(temp2);
//
//		Staff staffTemp = this.personService.getStaff(staff.getEmail());
//		if(staffTemp!=null){
//			redirectAttributes.addFlashAttribute("newStaffAdded2", "Employees email already exists!");
//			return "redirect:/home";
//		}
//		try {
//			Date tempDate = getFormatedDate(bd);
//			staff.setBirth_date(tempDate);
//			this.personService.addNewStaff(staff);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		redirectAttributes.addFlashAttribute("newStaffAdded", "Employees successfully added");
//		return "redirect:/home";
//	}

	@RequestMapping(value = "/editRestaurant", method = RequestMethod.POST)
	public String editRestaurant(@ModelAttribute("restoran1") Restaurant rest, RedirectAttributes redirectAttributes) {
		this.personService.updateRestaurant(rest);
		redirectAttributes.addFlashAttribute("updatedRest", "Restaurant successfully updated");
		return "redirect:/home";
	}
	
//	@RequestMapping(value = "home/rateMeal", method = RequestMethod.POST)
//	public String rateMeal(@RequestParam("rate") int rate,@RequestParam("orderId") int ordId, @RequestParam("food") int[] items) {
//		System.err.println(rate);
//		Order order = personService.findOrder(ordId);
//		order.setRate(rate);
//		personService.updateOrder(order);
//		for (int i = 0; i < items.length; i++) {
//			OrderedItem item = personService.findItem(items[i]);
//			int foodType = item.getFoodType();
//			int foodId = item.getFoodId();
//			if(foodId == 1){
//				Appetizer app = personService.findAppetizer(foodId);
//				double newRate = (app.getRate() + rate)/2;
//				round(newRate,2);
//				app.setRate(newRate);
//				personService.updateAppetizer(app);
//			}else if(foodId == 2){
//				MainCourse mc = personService.findMainCourse(foodId);
//				double newRate = (mc.getRate() + rate)/2;
//				round(newRate,2);
//				mc.setRate(newRate);
//				personService.updateMainCourse(mc);
//			}else if(foodId == 3){
//				Desert des = personService.findDesert(foodId);
//				double newRate = (des.getRate() + rate)/2;
//				round(newRate,2);
//				des.setRate(newRate);
//				personService.updateDesert(des);;
//			}else if( foodId == 4){
//				AlcoholicDrink adrink = personService.findAlchDrink(foodId);
//				double newRate = (adrink.getRate() + rate)/2;
//				round(newRate,2);
//				adrink.setRate(newRate);
//				personService.updateAlcoholicDrink(adrink);
//			}else if(foodId == 5){
//				NonAlcoholicDrink nadrink = personService.findNoNAlchDrink(foodId);
//				double newRate = (nadrink.getRate() + rate)/2;
//				round(newRate,2);
//				nadrink.setRate(newRate);
//				personService.updateNonAlcoholicDrink(nadrink);
//			}
//		}
////		this.personService.updateRestaurant(rest);
////		redirectAttributes.addFlashAttribute("updatedRest", "Restaurant successfully updated");
//		return "redirect:/home";
//	}
//	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
//	@RequestMapping(value = "home/updateOrder", method = RequestMethod.POST)
//	public String updateOrder(@RequestParam("itemId") int orId) {
//		System.err.println(orId);
//		OrderedItem order = personService.findItem(orId);
//		personService.deleteOrder(order);
////		this.personService.updateRestaurant(rest);
////		redirectAttributes.addFlashAttribute("updatedRest", "Restaurant successfully updated");
//		return "redirect:/home";
//	}
	

//	@RequestMapping(value = "home/uOrder", method = RequestMethod.POST)
//	public String uOrder(@RequestParam("itemId") int orId, @RequestParam("quanId") double quant) {
//		System.err.println(orId);
//		OrderedItem order = personService.findItem(orId);
//		order.setQuantity(quant);
//		personService.updateOrderedItem(order);
////		this.personService.updateRestaurant(rest);
////		redirectAttributes.addFlashAttribute("updatedRest", "Restaurant successfully updated");
//		return "redirect:/home";
//	}

//	@RequestMapping(value = "/newStaffShift", method = RequestMethod.POST)
//	public String addNewShiftStaff(@RequestParam("shift_entry") String naziv, @RequestParam("shift_date") String datum,
//			@RequestParam("reonNumber") String reonNumb, @RequestParam("staffID") String staffID,@RequestParam("restID") String restID,RedirectAttributes redirectAttributes)
//			throws ParseException {
//
//		Shift_schedule sc = new Shift_schedule();
//		sc.setShift_entry(naziv);
//		Date temp = getFormatedDate(datum);
//		sc.setShift_date(temp);
//		Staff stf = this.personService.getStaff(staffID);
//		
//		Set<Shift_schedule> listaSmenaRadnika = stf.getShift_schedule();
//		for(Shift_schedule shc:listaSmenaRadnika){
//			if(temp.compareTo(shc.getShift_date())==0 && shc.getShift_entry().equals(naziv)){
//				redirectAttributes.addFlashAttribute("smenaRadnika2", "Shift is already created!");
//				return "redirect:/home";
//			}
//		}
//		
//		
//		
//		
//		if (reonNumb.equals(" ")) {
//			System.out.println("YES");
//			this.personService.addNewStaffShift(sc);
//			stf.getShift_schedule().add(sc);
//			this.personService.refreshShift(stf);
//			redirectAttributes.addFlashAttribute("smenaRadnika", "Shift is successfully created!");
//			return "redirect:/home";
//		} else {
//			int brojReona = Integer.parseInt(reonNumb);
//			Restaurant r = this.personService.getRestaurant(Integer.parseInt(restID));
//			Reon ren = null;
//			
//			
//			System.out.println(ren.toString());
////			sc.setReonNum(brojReona);
//			this.personService.addNewStaffShift(sc);
//			stf.getShift_schedule().add(sc);
//			stf.getReons().add(ren);
//			this.personService.refreshShift(stf);
//			redirectAttributes.addFlashAttribute("smenaRadnika", "Shift is successfully created!");
//		}
//		
//		return "redirect:/home";
//	}
	
//	@RequestMapping(value = "/newDish", method = RequestMethod.POST)
//	public String addNewFood(@ModelAttribute("food") Food rest,@RequestParam("restID") String restID,RedirectAttributes redirectAttributes){
//		Restaurant r =this.personService.getRestaurant(Integer.parseInt(restID));
//	
//		if (rest.getType().equals("Appetizer")){
//			Appetizer a = new Appetizer();
//			a.setName(rest.getName());
//			a.setPrice(rest.getPrice());
//			a.setPicture(rest.getPicture());
//		
//			
//			this.personService.addAppetizer(a);
//		
//			
//		}
//		else if(rest.getType().equals("Desert")){
//			Desert a = new Desert();
//			a.setName(rest.getName());
//			a.setPrice(rest.getPrice());
//			a.setPicture(rest.getPicture());
//			
//			this.personService.addDesert(a);
//			
//		
//		}
//		else if(rest.getType().equals("MainCourse")){
//			MainCourse a = new MainCourse();
//			a.setName(rest.getName());
//			a.setPrice(rest.getPrice());
//			a.setPicture(rest.getPicture());
//			
//			this.personService.addMainCourse(a);
//			
//			
//		}
//		redirectAttributes.addFlashAttribute("newDishAdded", "Dish successfully added");
//		return "redirect:/home";
//	}
//	@RequestMapping(value = "/newDrink",method = RequestMethod.POST)
//	public String addNewDrink(@ModelAttribute("drink") Drink rest,@RequestParam("restID") String restID,RedirectAttributes redirectAttributes){
//		Restaurant r =this.personService.getRestaurant(Integer.parseInt(restID));
//		
//		
//		if (rest.getType().equals("Alcoholic")){
//			AlcoholicDrink ad = new AlcoholicDrink();
//			ad.setName(rest.getName());
//			ad.setPrice(rest.getPrice());
//			ad.setQuantity(rest.getQuantity());
//			ad.setPicture(rest.getPicture());
//			
//		}
//		else if (rest.getType().equals("NonAlcoholic")){
//			NonAlcoholicDrink ad = new NonAlcoholicDrink();
//			ad.setName(rest.getName());
//			ad.setPrice(rest.getPrice());
//			ad.setQuantity(rest.getQuantity());
//			ad.setPicture(rest.getPicture());
//			
//			
//			
//		}
//		redirectAttributes.addFlashAttribute("newDrinkAdded", "Drink successfully added");
//		
//		return "redirect:/home";
//	}
//	
//	@RequestMapping(value = "/editDish", method = RequestMethod.POST)
//	public String editFood(@ModelAttribute("foodd") Food rest,RedirectAttributes redirectAttributes){
//		
//		if (rest.getType().equals("Appetizer")){
//			System.out.println(rest.getItemID());
//			Appetizer a = this.personService.findAppetizer(rest.getItemID()); //new Appetizer();
//			a.setName(rest.getName());
//			a.setPrice(rest.getPrice());
//			if (!rest.getPicture().equals("")){
//				a.setPicture(rest.getPicture());
//			}
//			this.personService.updateAppetizer(a);
//			
//		}
//		else if(rest.getType().equals("Desert")){
//			Desert a = this.personService.findDesert(rest.getItemID());
//			a.setName(rest.getName());
//			a.setPrice(rest.getPrice());
//			if (!rest.getPicture().equals("")){
//				a.setPicture(rest.getPicture());
//			}
//			this.personService.updateDesert(a);
//		}
//		else if(rest.getType().equals("MainCourse")){
//			MainCourse a = this.personService.findMainCourse(rest.getItemID());
//			a.setName(rest.getName());
//			a.setPrice(rest.getPrice());
//			if (!rest.getPicture().equals("")){
//				a.setPicture(rest.getPicture());
//			}
//			this.personService.updateMainCourse(a);
//		}
//		redirectAttributes.addFlashAttribute("DishUpdated", "Dish successfully updated");
//		return "redirect:/home";
//	}
//	
//	@RequestMapping(value = "/editDrink",method = RequestMethod.POST)
//	public String editDrink(@ModelAttribute("drinkk") Drink rest,RedirectAttributes redirectAttributes){
//		System.out.println(rest.getId() + " "+rest.getName());
//		if (rest.getType().equals("Alcoholic")){
//			AlcoholicDrink ad = this.personService.findAlchDrink(rest.getId());
//			ad.setName(rest.getName());
//			ad.setPrice(rest.getPrice());
//			ad.setQuantity(rest.getQuantity());
//			if (!rest.getPicture().equals("")){
//				ad.setPicture(rest.getPicture());
//			}
//			this.personService.updateAlcoholicDrink(ad);
//		}
//		else if (rest.getType().equals("NonAlcoholic")){
//			NonAlcoholicDrink ad = this.personService.findNoNAlchDrink(rest.getId());
//			ad.setName(rest.getName());
//			ad.setPrice(rest.getPrice());
//			ad.setQuantity(rest.getQuantity());
//			if (!rest.getPicture().equals("")){
//				ad.setPicture(rest.getPicture());
//			}
//			this.personService.updateNonAlcoholicDrink(ad);
//		}
//		redirectAttributes.addFlashAttribute("DrinkUpdated", "Drink successfully updated");
//		return "redirect:/home";
//	}
	
//	@RequestMapping(value = "/addNewItem",method = RequestMethod.POST)
//	public String addNewItem(@ModelAttribute ("item") FoodItem fi,RedirectAttributes redirectAttributes){
//		
//		FoodItem name = this.personService.findFoodItem(fi.getName());
//		if (name!=null){
//			redirectAttributes.addFlashAttribute("newItemAdded2", "Item with that name already exists!");
//			return "redirect:/home";
//		}
//		this.personService.addFoodItem(fi);
//		redirectAttributes.addFlashAttribute("newItemAdded", "Item successfully added!");
//		return "redirect:/home";
//
//	}
//	
//	@RequestMapping(value = "/newRestShift",method = RequestMethod.POST)
//	public String newRestShift(@RequestParam("idRest") String restID,@RequestParam("startTime") String start,@RequestParam("endTime") String end,@RequestParam("name") String name,RedirectAttributes redirectAttributes){
//		
//		Shift ns = new Shift();
//		long startTime = 0;
//		long endTime = 0;
//		Time st = null;
//		Time en = null;
//		Restaurant rest = this.personService.getRestaurant(Integer.parseInt(restID));
//		
//		try {
//			startTime = new SimpleDateFormat("hh:mm:ss").parse(start).getTime();
//			endTime = new SimpleDateFormat("hh:mm:ss").parse(end).getTime();
//			
//			ns.setShift_entry(name);
//			st = new Time(startTime);
//			en = new Time(endTime);
//			
//			ns.setEnd_shift(en);
//			ns.setStart_shift(st);
//			ns.setRestaurant(rest);
//			this.personService.addNewRestShift(ns);
//			redirectAttributes.addFlashAttribute("shiftRestAdded","Shift is successflly added!");
//		}
//		catch(ParseException e){
//			redirectAttributes.addFlashAttribute("shiftRestAdded2","Enter a valid time format!");
//		}
//		return "redirect:/home";
//
//	}
//	
//	@RequestMapping(value = "/NewReonType",method = RequestMethod.POST)
//	public String NewReonType(@ModelAttribute("type") ReonTypes rt,RedirectAttributes redirectAttributes){
//		
//		List<ReonTypes> tipovi = this.personService.getAllReaonTypes();
//		for(ReonTypes rtt:tipovi){
//			if(rtt.getName().equals(rt.getName())){
//				redirectAttributes.addFlashAttribute("reonTypeAdded","Type with that name already exist!");
//				return "redirect:/home";
//			}
//		}
//		
//		this.personService.addNewReonType(rt);
//		redirectAttributes.addFlashAttribute("reonTypeAdded1","Type is successflly added!");
//		return "redirect:/home";
//
//	}
//	
//	@RequestMapping(value = "/newRestReon",method = RequestMethod.POST)
//	public String newRestReon(@RequestParam("idRest") String restID,@RequestParam("reonType_id") String rtID,RedirectAttributes redirectAttributes){
//		
//		Restaurant rest = this.personService.getRestaurant(Integer.parseInt(restID));
//		ReonTypes rt = this.personService.findReonTypes(Integer.parseInt(rtID));
//		List<Integer> restReons = new ArrayList<Integer>();
//		
//		for(Reon reon:rt.getReoni()){
//			if(reon.getRestaurant().getId()==rest.getId()){
//				redirectAttributes.addFlashAttribute("newReonToRest","Reon is already exists!");
//				return "redirect:/home";
//			}
//		}
//		
//		Collections.sort(restReons);
//		int reonNum = restReons.get((restReons.size()-1))+1;
//		
//		Reon noviReon = new Reon();
//		
//		noviReon.setReon_num(reonNum);
//		noviReon.setRestaurant(rest);
//		this.personService.addNewReonToRest(noviReon);
//		
//		rest.setReon_num(rest.getReon_num()+1);
//		this.personService.updateRestaurant(rest);
//		
//		rt.getReoni().add(noviReon);
//		
//		this.personService.refresType(rt);
//		redirectAttributes.addFlashAttribute("newReonToRest","Reon is successflly added!");
//		return "redirect:/home";
//
//	}
//	
//	public Date getFormatedDate(String date) throws ParseException {
//
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//		java.util.Date parsed = format.parse(date);
//		Date sql = new Date(parsed.getTime());
//		return sql;
//	}
//	
//	public CalendarJSONShow getJSONFormat(Shift_schedule smena){
//		CalendarJSONShow cs = new CalendarJSONShow();
//		cs.setTitle(smena.getShift_entry());
//		cs.setStart(smena.getShift_date());
//		return cs;
//	}

}
