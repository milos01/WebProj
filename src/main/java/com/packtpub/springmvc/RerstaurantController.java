package com.packtpub.springmvc;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.packtpub.springmvc.model.Restaurant;
import com.packtpub.springmvc.model.Role;
import com.packtpub.springmvc.model.Staff;
import com.packtpub.springmvc.model.TableOne;
import com.packtpub.springmvc.model.Table_schedule;
import com.packtpub.springmvc.service.PersonService;

@Controller
public class RerstaurantController {
	private PersonService personService;

	@Autowired(required = true)
	@Qualifier(value = "personService")
	public void setPersonService(PersonService ps) {
		this.personService = ps;
	}

	@RequestMapping("/restaurant/{id}")
	public String restaurantHome(@PathVariable(value = "id") final int id, RedirectAttributes redirectAttributes,Model model, HttpSession session ) {
		if(null == session.getAttribute("logedUser")){
			return "redirect:/";
		}
		redirectAttributes.addFlashAttribute("id", id);
		Restaurant restaurant = this.personService.getRestaurant(id);
		List<TableOne> tables = this.personService.allTables(id);
		
		
		
//		List<Table_schedule> checkedTables = personService.checkForFreeTables("", redirectAttributes, res_to, guestNum);
//		List<Table_schedule> checkedTables = (List) request.getSession().getAttribute("checkedTables1");
//		System.err.println(checkedTables);
//		if (checkedTables != null) {
//			model.addAttribute("checkedTables", checkedTables);
////			for (Table_schedule table_schedule : ts) {
////				System.err.println(table_schedule.getId());
////			}
//		}
		
		model.addAttribute("restaurant", restaurant);
		model.addAttribute("tables", tables);
		return "restaurant";

	}
	@RequestMapping(value = "/registerRestaurant", method = RequestMethod.POST)
	public String home3( @ModelAttribute("Restaurant")  @Valid Restaurant r, BindingResult bindres, @RequestParam("manName")String manName, @RequestParam("manLastName")String manLastName, @RequestParam("manEmail")String manEmail, RedirectAttributes redirectAttributes, Model model, HttpSession session){
		//Map modelMap = model.asMap();
		/*
		for (Object modelKey : modelMap.keySet()) {
			Object modelValue = modelMap.get(modelKey);
			System.out.println(modelKey + " -- " + modelValue);
		}
		*/
		
		if(manName.equals("")){
			bindres.addError(new ObjectError("manName", "Manager's name must not be empty"));
		}
		if(manLastName.equals("")){
			bindres.addError(new ObjectError("manLastName", "Manager's last name must not be empty"));
		}
		if(manEmail.equals("")){
			bindres.addError(new ObjectError("manEmail", "Manager's email must not be empty"));
		}
		if (bindres.hasErrors()) {
			redirectAttributes.addFlashAttribute("errors", bindres);
			System.out.println("ima error");
			return "redirect:/home";
		}
		
		List<Restaurant> restaurants = this.personService.listRestaurants();
		
		for(Restaurant rr : restaurants){
			if(rr.getName().equalsIgnoreCase(r.getName())){
				bindres.addError(new ObjectError("restName", "Restaurant with that name already exists"));
				redirectAttributes.addFlashAttribute("errors", bindres);
				System.out.println("1");
				return "redirect:/home";
			}
		}
		List<Staff> staffs = this.personService.listStaffs();
		for(Staff ss : staffs){
			if(ss.getRole().getRoleName().equalsIgnoreCase("manager")){
				if((ss.getFirstName().equalsIgnoreCase(manName) && ss.getLastName().equalsIgnoreCase(manLastName)) || ss.getEmail().equalsIgnoreCase(manEmail)){
					bindres.addError(new ObjectError("managerName", "Manager with that name (email) already exists"));
					redirectAttributes.addFlashAttribute("errors", bindres);
					System.out.println("2");
					return "redirect:/home";
				}
			}
		}
		
		r.setDesription("");
		r.setEmail("");
		r.setOpen_hours("");
		r.setPhone("");
		r.setRate(0);
		r.setCity("");
		this.personService.addRestaurant(r);
		
		Staff s = new Staff();
		s.setFirstName(manName);
		s.setLastName(manLastName);
		s.setEmail(manEmail);
		Role temp = this.personService.getRole(5);
		s.setRole(temp);
		s.setRestaurant(r);
		s.setCon_num(" ");
		s.setPassword(" ");
		s.setShoe_num(0);
		s.setBirth_date(new Date(1950, 1, 1));
		this.personService.addNewStaff(s);
		restaurants.add(r);
		
		model.addAttribute("restaurants", restaurants);
		
		return "redirect:/home";
	}
}
