package com.packtpub.springmvc;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.packtpub.springmvc.model.Event;
import com.packtpub.springmvc.model.Recension;
import com.packtpub.springmvc.model.Restaurant;
import com.packtpub.springmvc.model.Role;
import com.packtpub.springmvc.model.User;
import com.packtpub.springmvc.pojo.AddRecensionPojo;
import com.packtpub.springmvc.pojo.NewEventPojo;
import com.packtpub.springmvc.pojo.NewRestaurantPojo;
import com.packtpub.springmvc.pojo.RemoveRecensionPojo;
import com.packtpub.springmvc.pojo.getRestaurantPojo;
import com.packtpub.springmvc.pojo.userParamsPojo;
import com.packtpub.springmvc.service.PersonService;

@Controller
public class RerstaurantController {
	private PersonService personService;

	@Autowired(required = true)
	@Qualifier(value = "personService")
	public void setPersonService(PersonService ps) {
		this.personService = ps;
	}
	
	@RequestMapping(value="/getusersrestaurant", method = RequestMethod.GET ,headers="Accept=*/*",  produces="application/json")
	public ResponseEntity<List<Restaurant>> getUserRestaurants(HttpSession session){
		System.err.println("aaaaaaaa");
		User u = (User)session.getAttribute("logedUser");
		List <Restaurant> res = personService.getUsersRestaurants(u.getId());
		for (Restaurant restaurant : res) {
			System.err.println(restaurant.getUser().getFirstName());
		}
		return new ResponseEntity<List<Restaurant>>(res, HttpStatus.OK);
	}
	
	@RequestMapping(value="/restaurant/{id}/getRestaurantObj", method = RequestMethod.POST ,headers="Accept=*/*")
	public ResponseEntity <Restaurant>getRestaurantf(@RequestBody getRestaurantPojo rpp){	
		Restaurant res = this.personService.findRestaurant(rpp.getIdd());

		return new ResponseEntity<Restaurant> (res, HttpStatus.OK);

	}
	
	@RequestMapping(value="/addRestaurant", method = RequestMethod.POST ,headers="Accept=*/*")
	public ResponseEntity <Restaurant>addRestaurants(@RequestBody NewRestaurantPojo rpp,HttpSession session){
		User u = (User)session.getAttribute("logedUser");
		
		Restaurant res = new Restaurant();
		res.setUser(u);
		res.setAddress(rpp.getAddress());
		res.setCity(rpp.getCity());
		res.setEmail(rpp.getEmail());
		res.setName(rpp.getName());
		res.setPhone(rpp.getPhone());
		res.setPib(rpp.getPib());
		res.setPicture(rpp.getPicture());
		res.setSite(rpp.getSite());
		res.setZiroRacun(rpp.getZiroRacun());
		
		this.personService.addRestaurant(res);
		
		return new ResponseEntity<Restaurant> (res, HttpStatus.OK);
	}
	
	@RequestMapping(value="/updateRestaurant", method = RequestMethod.POST ,headers="Accept=*/*")
	public ResponseEntity <Restaurant>updateRestaurants(@RequestBody NewRestaurantPojo rpp,HttpSession session){
		User u = (User)session.getAttribute("logedUser");
		
		Restaurant res = new Restaurant();
		res.setUser(u);
		res.setAddress(rpp.getAddress());
		res.setCity(rpp.getCity());
		res.setEmail(rpp.getEmail());
		res.setName(rpp.getName());
		res.setPhone(rpp.getPhone());
		res.setPib(rpp.getPib());
		res.setPicture(rpp.getPicture());
		res.setSite(rpp.getSite());
		res.setZiroRacun(rpp.getZiroRacun());
		
		this.personService.addRestaurant(res);
		
		return new ResponseEntity<Restaurant> (res, HttpStatus.OK);
	}
	
	@RequestMapping(value="/addRecension", method = RequestMethod.POST ,headers="Accept=*/*",  produces="application/json")
	public ResponseEntity<Recension> addRece(@RequestBody AddRecensionPojo rpp,HttpSession session){
			User u = (User)session.getAttribute("logedUser");
			Restaurant rr = this.personService.getRestaurant(rpp.getRid());
			Recension rec = new Recension();
			rec.setOcena(rpp.getOcena());
			rec.setRestaurant(rr);
			rec.setText(rpp.getText());
			rec.setDatum(rpp.getDatum());
			rec.setUser(u);
			this.personService.addRecension(rec);
			return new ResponseEntity<Recension>(rec, HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/removeRecension", method = RequestMethod.POST ,headers="Accept=*/*",  produces="application/json")
	public ResponseEntity<Recension> removeRecension(@RequestBody RemoveRecensionPojo rpp,HttpSession session){
			
			
			this.personService.removeRecension(rpp.getResid());
			return new ResponseEntity<Recension>(HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/removeRestaurant", method = RequestMethod.POST ,headers="Accept=*/*",  produces="application/json")
	public ResponseEntity<Restaurant> removeRestaurant(@RequestBody RemoveRecensionPojo rpp,HttpSession session){
			
			
			this.personService.removeRestaurant(rpp.getResid());
			return new ResponseEntity<Restaurant>(HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/saveImage", method = RequestMethod.POST )
	public ResponseEntity<Recension> saveImage(@RequestParam("file") MultipartFile file,HttpSession session){
			
			
			System.err.println("aa");
			return null;
		
	}

	@RequestMapping("/restaurant/{id}")
	public String restaurantHome(@PathVariable(value = "id") final int id, RedirectAttributes redirectAttributes,Model model, HttpSession session ) {
//		if(null == session.getAttribute("logedUser")){
//			return "redirect:/";
//		}
//		User u = (User) session.getAttribute("logedUser");
//		Restaurant restaurant = this.personService.getRestaurant(id);
//		
//		if (!u.getRole().getRoleName().equals("Bidder")){
//			
//			redirectAttributes.addFlashAttribute("id", id);
//			List<TableOne> tables = this.personService.allTables(id);
//			
//			
//			
//	//		List<Table_schedule> checkedTables = personService.checkForFreeTables("", redirectAttributes, res_to, guestNum);
//	//		List<Table_schedule> checkedTables = (List) request.getSession().getAttribute("checkedTables1");
//	//		System.err.println(checkedTables);
//	//		if (checkedTables != null) {
//	//			model.addAttribute("checkedTables", checkedTables);
//	////			for (Table_schedule table_schedule : ts) {
//	////				System.err.println(table_schedule.getId());
//	////			}
//	//		}
//			
//			model.addAttribute("restaurant", restaurant);
//			model.addAttribute("tables", tables);
//		}
//		else {
//			List<FoodListItem> ponude = new ArrayList<FoodListItem>();
//			
//			List<Offer> listaPonuda = new ArrayList<Offer>();
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
//			List<FoodItem> listaItema = this.personService.GetAllFoodItems();
//			model.addAttribute("listaPonuda", listaPonuda);
//			model.addAttribute("grocList",grocList);
//			model.addAttribute("listaSvihItema",listaItema);
//			model.addAttribute("ponude",ponude);
//			model.addAttribute("restaurant", restaurant);
//		}
		return "restaurant";

	}
	
//	
//	@RequestMapping(value = "/registerRestaurant", method = RequestMethod.POST)
//	public String home3( @ModelAttribute("Restaurant")  @Valid Restaurant r, BindingResult bindres, @RequestParam("manName")String manName, @RequestParam("manLastName")String manLastName, @RequestParam("manEmail")String manEmail, RedirectAttributes redirectAttributes, Model model, HttpSession session){
//		//Map modelMap = model.asMap();
//		/*
//		for (Object modelKey : modelMap.keySet()) {
//			Object modelValue = modelMap.get(modelKey);
//			System.out.println(modelKey + " -- " + modelValue);
//		}
//		*/
//		
//		if(manName.equals("")){
//			bindres.addError(new ObjectError("manName", "Manager's name must not be empty"));
//		}
//		if(manLastName.equals("")){
//			bindres.addError(new ObjectError("manLastName", "Manager's last name must not be empty"));
//		}
//		if(manEmail.equals("")){
//			bindres.addError(new ObjectError("manEmail", "Manager's email must not be empty"));
//		}
//		if (bindres.hasErrors()) {
//			redirectAttributes.addFlashAttribute("errors", bindres);
//			System.out.println("ima error");
//			return "redirect:/home";
//		}
//		
//		List<Restaurant> restaurants = this.personService.listRestaurants();
//		
//		for(Restaurant rr : restaurants){
//			if(rr.getName().equalsIgnoreCase(r.getName())){
//				bindres.addError(new ObjectError("restName", "Restaurant with that name already exists"));
//				redirectAttributes.addFlashAttribute("errors", bindres);
//				System.out.println("1");
//				return "redirect:/home";
//			}
//		}
//		List<Staff> staffs = this.personService.listStaffs();
//		for(Staff ss : staffs){
//			if(ss.getRole().getRoleName().equalsIgnoreCase("manager")){
//				if((ss.getFirstName().equalsIgnoreCase(manName) && ss.getLastName().equalsIgnoreCase(manLastName)) || ss.getEmail().equalsIgnoreCase(manEmail)){
//					bindres.addError(new ObjectError("managerName", "Manager with that name (email) already exists"));
//					redirectAttributes.addFlashAttribute("errors", bindres);
//					System.out.println("2");
//					return "redirect:/home";
//				}
//			}
//		}
//		
//		r.setDesription("");
//		r.setEmail("");
//		r.setOpen_hours("");
//		r.setPhone("");
//		r.setRate(0);
//		r.setCity("");
//		this.personService.addRestaurant(r);
//		
//		Staff s = new Staff();
//		s.setFirstName(manName);
//		s.setLastName(manLastName);
//		s.setEmail(manEmail);
//		Role temp = this.personService.getRole(5);
//		s.setRole(temp);
//		s.setRestaurant(r);
//		s.setCon_num(" ");
//		s.setPassword(" ");
//		s.setShoe_num(0);
//		s.setBirth_date(new Date(1950, 1, 1));
//		this.personService.addNewStaff(s);
//		restaurants.add(r);
//		
//		model.addAttribute("restaurants", restaurants);
//		
//		return "redirect:/home";
//	}
}
